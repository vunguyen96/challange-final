package vn.axonactive.cloudnative.service.client.payment;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Singleton
@RegisterRestClient
@RegisterClientHeaders
@Path("/wallets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface WalletClient {
	
	@GET
    @Path("/ping")
    public Response ping() ;

    @GET
    public Response list();

    @GET
    @Path("/{userId}")
    public Response get(@PathParam("userId") String userId);

    @PUT
    @Path("/{userId}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response update(@PathParam("userId") String userId, @FormParam(value = "cost") int cost) ;
}
