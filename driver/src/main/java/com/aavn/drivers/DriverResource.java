package com.aavn.drivers;

import java.util.stream.StreamSupport;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/drivers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DriverResource {

	@Inject
	DriverService driverService;

	@ConfigProperty(name = "greeting", defaultValue = "Hello")
	String greeting;

	@GET
	public Response getAll() {
		return Response.ok(driverService.getAll()).build();
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
