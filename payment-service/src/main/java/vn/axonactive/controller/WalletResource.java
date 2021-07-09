package vn.axonactive.controller;

import java.util.Optional;
import java.util.stream.StreamSupport;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import vn.axonactive.model.Wallet;
import vn.axonactive.service.WalletService;

@Path("wallets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WalletResource {

    @Inject
    WalletService walletService;

	@ConfigProperty(name = "greeting", defaultValue = "Hello")
	String greeting;
    
    @GET
    @Path("/ping")
    public Response ping() {
    	return Response.ok(greeting).build();
    }

    @GET
    public Response list() {
        return Response.ok(walletService.getAll()).build();
    }

    @POST
    public Response init() {
        walletService.init();
        return Response.ok().build();
    }

    @GET
    @Path("/{userName}")
    public Response get(@PathParam("userName") String userName) {
        Optional<Wallet> walletOpt = walletService.getByUserName(userName);
        if (walletOpt.isPresent()) {
            return Response.ok(walletOpt.get()).build();
        } else {
            return Response.status(Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("/{userName}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response update(@PathParam("userName") String userName, @FormParam(value = "cost") int cost) {
        Optional<Wallet> walletOpt = walletService.getByUserName(userName);
        if (walletOpt.isPresent()) {
            Wallet wallet = walletOpt.get();
            if (cost > wallet.getBalance()) {
                return Response.status(Status.BAD_REQUEST).build();
            }
            return Response.ok(walletService.pay(wallet, cost)).build();
        } else {
            return Response.status(Status.BAD_REQUEST).build();
        }
    }
    
	@Path("/configurations")
	@GET
	public Response getConfigurations() throws JsonProcessingException {
		Config configurations = ConfigProvider.getConfig();
		JsonObjectBuilder responseBuilder = Json.createObjectBuilder();
		ObjectMapper mapper = new ObjectMapper();
		StreamSupport.stream(configurations.getConfigSources().spliterator(), false)
				.filter((config) -> config.getName().contains(".properties"))
				.forEach((config) -> config.getProperties().forEach(responseBuilder::add));
		return Response.ok(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseBuilder.build())).build();
	}
}