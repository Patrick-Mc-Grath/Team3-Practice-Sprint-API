package com.kainos.ea.controller;

import com.kainos.ea.dao.BandDao;
import com.kainos.ea.exception.FailedToGetBandsException;
import com.kainos.ea.service.BandService;
import com.kainos.ea.util.DatabaseConnector;
import io.swagger.annotations.Api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    @Path("/bands")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBands() {
        try {
            return Response.ok(bandService.getAllBands()).build();
        } catch(FailedToGetBandsException e) {
            return Response.serverError().build();
        }
    }
}
