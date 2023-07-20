package com.kainos.ea.controller;

import com.kainos.ea.dao.RoleBandDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.FailedToGetRoleBandsException;
import com.kainos.ea.model.RoleBandResponse;
import com.kainos.ea.service.RoleBandService;
import com.kainos.ea.util.DatabaseConnector;
import io.swagger.annotations.Api;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Api("Backend API for Role Bands")
@Path("/api")

public class RoleBandController{

    private RoleBandService roleBandService;

    public RoleBandController(){
        roleBandService = new RoleBandService(new RoleBandDao(), new DatabaseConnector());
    }

    public RoleBandController(RoleBandService roleBandService){
        this.roleBandService = roleBandService;
    }

    @GET
    @Path("/role-band-levels")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRoleBands() {
        try {
            List<RoleBandResponse> roleBandList = roleBandService.getRoleBands();
            return Response.ok().entity(roleBandList).build();
        } catch (DatabaseConnectionException | FailedToGetRoleBandsException e) {
            return Response.serverError().build();
        }
    }

}
