package vn.axonactive.cloudnative.service.order;

import java.util.List;
import java.util.stream.StreamSupport;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import vn.axonactive.cloudnative.service.client.driver.ShippingClient;
import vn.axonactive.cloudnative.service.client.driver.model.Shipping;
import vn.axonactive.cloudnative.service.client.payment.WalletClient;
import vn.axonactive.cloudnative.service.order.dto.OrderDto;
import vn.axonactive.cloudnative.service.order.service.OrderService;
import vn.axonactive.cloudnative.service.order.service.WalletService;

@Path("/orders")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {
	
	@ConfigProperty(name = "greeting", defaultValue = "Hello")
	String greeting;

    @Inject
    OrderService orderService;
    
    @Inject
    WalletService walletServcie;
    
    @Inject
    @RestClient
    WalletClient walletClient;
    
    @Inject
    @RestClient
    ShippingClient shippingClient;
    
    @POST
    public Response createOrder(String order) {
     	OrderDto orderCreation = new OrderDto();
		try {
			OrderDto orderDto = orderService.convertToOrderDto(order);
			orderCreation.setOrderItems(orderDto.getOrderItems());
			orderCreation.setUserName(orderDto.getUserName());
		} catch (JsonProcessingException e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		
    	boolean badRequest = false;
    	
    	Response updateWalletResponse = walletServcie.updateWallet(walletClient, orderCreation);
    	if (!orderService.isStatusOk(updateWalletResponse)) {
    		badRequest = true;;
    	}
    	
    	Response shippingResponse = shippingClient.createOrder();
    	Shipping shippingEntry = shippingResponse.readEntity(Shipping.class);
    	if (!orderService.isStatusOk(shippingResponse) || shippingEntry == null) {
    		badRequest = true;
    	}
    	
    	orderCreation.setDriverId(String.valueOf(shippingEntry.getDriverId()));
    	OrderDto orderEntry = orderService.createOrder(orderCreation);
    	if (orderEntry == null) {
    		badRequest = true;
    	}
    	
    	if (badRequest == true) {
    		return Response.status(Status.BAD_REQUEST).build();
    	}
    	return Response.status(Status.OK).entity("Register successful").build();
    }

    @GET
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }
    
    @GET
    @Path("/ping")
    public Response ping() {
    	return Response.status(Status.OK).entity(greeting).build();
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
