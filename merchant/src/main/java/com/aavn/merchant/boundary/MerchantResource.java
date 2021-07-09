package com.aavn.merchant.boundary;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.StreamSupport;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.aavn.merchant.entity.Merchant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.quarkus.security.Authenticated;

@Path("/merchants")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MerchantResource {
	
	@ConfigProperty(name = "greeting", defaultValue = "Hello")
	String greeting;

	private Set<Merchant> merchants = new HashSet<>();
    
    public MerchantResource() {
        Merchant merchant1 = new Merchant("m01", "The coffe house", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRJdYwrfkqNFPThm8l7suDMt-yZO2bbROR46g&usqp=CAUhttps://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRTIJY-Djq6A8s3QcOuDTBF6KVL20GVaH4pQA&usqp=CAU");
        
        merchants.add(merchant1);
        
        Merchant merchant2 = new Merchant("m02", "Highlands Coffee", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSTIY4VU0M6WzYbnD2Hkk81a73uccht_1wR7Q&usqp=CAU");
        merchants.add(merchant2);
        
        Merchant merchant3 = new Merchant("m03", "Happy Dragon", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTHAtzATkm9bGT0IvAFItNP7c0T5ptlApRK4A&usqp=CAU");
        merchants.add(merchant3);
    }

    @GET
    @Authenticated
    public Response getAllMerchants() {
        return Response.ok(merchants).build();
    }
    
    @GET
    @Path("{id}")
    public Response getMerchant(@PathParam("id") String id) {
    	Merchant merchant = merchants.stream().filter(existingMerchant -> existingMerchant.getId().equals(id)).findFirst().orElseThrow(NotFoundException::new);
        return Response.ok(merchant).build();
    }
    
	@Path("/ping")
	@GET
	public Response ping() {
		return Response.ok(greeting).build();
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
