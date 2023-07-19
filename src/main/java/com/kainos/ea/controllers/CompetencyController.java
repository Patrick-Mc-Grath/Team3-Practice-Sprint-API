package com.kainos.ea.controllers;

import com.kainos.ea.service.CompetencyService;
import com.kainos.ea.client.FailedToGetCompsException;
import io.swagger.annotations.Api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Engineering Academy Dropwizard Competency API")
@Path("/api")
public class CompetencyController {

    private CompetencyService competencyService = new CompetencyService();

    @GET
    @Path("/competencies")
    @Produces(MediaType.APPLICATION_JSON)

    public Response getCompetenciesWithBand() {

        try {
            return Response.ok(competencyService.getAllCompsWithBand()).build();
        } catch (FailedToGetCompsException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }


}
