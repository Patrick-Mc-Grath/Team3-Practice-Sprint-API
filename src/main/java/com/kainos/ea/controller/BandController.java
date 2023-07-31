package com.kainos.ea.controller;

import com.kainos.ea.dao.BandDao;
import com.kainos.ea.exception.BandDoesNotExistException;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.TrainingDoesNotExistException;
import com.kainos.ea.service.BandService;
import com.kainos.ea.util.DatabaseConnector;
import io.swagger.annotations.Api;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Backend API Bands")
@Path("/api")
public class BandController {
    private BandService bandService;

    public BandController() {
        this.bandService = new BandService(new BandDao(), new DatabaseConnector());
    }

    public BandController(BandService bandService) {
        this.bandService = bandService;
    }

    @GET
    @Path("/bands/{bandId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBandById(@PathParam("bandId") int bandId) {
        try{
            return Response.status(HttpStatus.OK_200).entity(bandService.getBandById(bandId)).build();
        } catch (BandDoesNotExistException e) {
            return Response.status(HttpStatus.BAD_REQUEST_400).build();
        } catch (SQLException | DatabaseConnectionException e) {
            System.err.println(e.getMessage());
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        }
    }
}
