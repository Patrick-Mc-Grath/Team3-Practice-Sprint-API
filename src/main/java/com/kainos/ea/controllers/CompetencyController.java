package com.kainos.ea.controllers;

import com.kainos.ea.db.CompetencyDao;
import com.kainos.ea.db.DatabaseConnector;
import com.kainos.ea.service.CompetencyService;
import com.kainos.ea.client.FailedToGetCompsException;
import io.swagger.annotations.Api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Engineering Academy Dropwizard Competency API")
@Path("/api")
public class CompetencyController {

    private CompetencyService competencyService = new CompetencyService(new CompetencyDao(), new DatabaseConnector());

    @GET
    @Path("/competencies/{bandId}")
    @Produces(MediaType.APPLICATION_JSON)

    public Response getCompetenciesWithBand(@PathParam("bandId") int bandId) {

        try {
            return Response.ok(competencyService.getAllCompsWithBand(bandId)).build();
        } catch (FailedToGetCompsException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }


}
