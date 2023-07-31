package com.kainos.ea.controller;

import com.kainos.ea.dao.TrainingDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.FailedToGetTrainingException;
import com.kainos.ea.exception.TrainingDoesNotExistException;
import com.kainos.ea.service.TrainingService;
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

@Api("Backend API Training")
@Path("/api")
public class TrainingController {

    private TrainingService trainingService;

    public TrainingController() {
        trainingService = new TrainingService(new TrainingDao(), new DatabaseConnector());
    }

    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @GET
    @Path("/training/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTrainingByBand(@PathParam("id") int bandId) {
        try{
            return Response.status(HttpStatus.OK_200).entity(trainingService.getTrainingByBand(bandId)).build();
        } catch (TrainingDoesNotExistException e) {
            return Response.status(HttpStatus.BAD_REQUEST_400).build();
        } catch (SQLException | DatabaseConnectionException e) {
            System.err.println(e.getMessage());
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        }
    }

    @GET
    @Path("/training-categories")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTrainingCategories(){
        try {
            return Response.status(HttpStatus.OK_200).entity(trainingService.getTrainingCategories()).build();
        } catch (SQLException | DatabaseConnectionException e){
            System.err.println(e.getMessage());
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        }
    }

}
