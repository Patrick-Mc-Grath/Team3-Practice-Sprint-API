package com.kainos.ea.controller;
import com.kainos.ea.dao.CapabilitiesDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.service.CapabilitiesService;
import com.kainos.ea.util.DatabaseConnector;
import io.swagger.annotations.Api;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
@Api("API Hack Street Boys")
@Path("/api")
public class CapabilityController {

    private CapabilitiesService capabilitiesService;

    public CapabilityController()
    {
        capabilitiesService = new CapabilitiesService(new CapabilitiesDao(), new DatabaseConnector());
    }

    public CapabilityController(CapabilitiesService capabilitiesService) {this.capabilitiesService = capabilitiesService;}

    @GET
    @Path("/capabilities")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCapabilities()
    {
        try
        {
            return Response.ok(capabilitiesService.getCapabilities()).build();
        } catch (SQLException e)
        {
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        } catch (DatabaseConnectionException e) {

            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        }
    }
}
