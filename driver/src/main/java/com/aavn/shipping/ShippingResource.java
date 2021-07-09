package com.aavn.shipping;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.aavn.http.MessageRespone;
import com.aavn.shipping.model.Shipping;

@Path("/shipping")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ShippingResource {

  @Inject
  ShippingService orderService;

  @POST
  public Response createOrder() {
    Shipping order = orderService.createOrder();
    if (null == order) {
      return Response.status(400).entity(MessageRespone.create("Could not create order!")).build();
    }
    return Response.ok().entity(order).build();
  }
}
