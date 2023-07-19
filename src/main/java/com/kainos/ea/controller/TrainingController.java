package com.kainos.ea.controller;

import com.kainos.ea.exception.FailedToGetTrainingException;
import com.kainos.ea.exception.TrainingDoesNotExistException;
import com.kainos.ea.service.TrainingService;
import io.swagger.annotations.Api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Training")
@Path("/api")
public class TrainingController {

    TrainingService trainingService = new TrainingService();

    @GET
    @Path("/training/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTrainingByBand(@PathParam("id") int bandId) {
        try{
            return Response.ok(trainingService.getTrainingByBand(bandId)).build();
        } catch (FailedToGetTrainingException e) {
            return Response.serverError().build();
        } catch (TrainingDoesNotExistException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

}
