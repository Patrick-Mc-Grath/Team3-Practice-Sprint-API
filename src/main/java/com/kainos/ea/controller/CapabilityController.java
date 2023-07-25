package com.kainos.ea.controller;

import com.kainos.ea.dao.CapabilityDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.DescriptionLengthException;
import com.kainos.ea.exception.NameLengthException;
import com.kainos.ea.model.CapabilityRequest;
import com.kainos.ea.service.CapabilityService;
import com.kainos.ea.util.DatabaseConnector;
import com.kainos.ea.validator.CapabilityValidator;
import io.swagger.annotations.Api;
import org.eclipse.jetty.http.HttpStatus;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Backend API Capabilities")
@Path("/api")
public class CapabilityController {

    private CapabilityService capabilityService;

    public CapabilityController() {
        capabilityService = new CapabilityService(new CapabilityDao(), new DatabaseConnector());
    }

    public CapabilityController(CapabilityService capabilityService) {
        this.capabilityService = capabilityService;
    }

    CapabilityValidator capabilityValidator = new CapabilityValidator();



    @POST
    @Path("/capabilities")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCapability(CapabilityRequest cap) throws DatabaseConnectionException, SQLException, NameLengthException, DescriptionLengthException {
        try {
            if (capabilityValidator.isValidCapability(cap)) {
                try {
                    int id = capabilityService.insertCapability(cap);
                    return Response.status(HttpStatus.CREATED_201).entity(id).build();
                } catch (DatabaseConnectionException | SQLException e) {
                    System.out.println(e);
                    return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
                }
            } else {
                return Response.status(HttpStatus.BAD_REQUEST_400).build();
            }
        } catch (NameLengthException | DescriptionLengthException e) {
            return Response.status(HttpStatus.BAD_REQUEST_400).build();
        }
    }

}
