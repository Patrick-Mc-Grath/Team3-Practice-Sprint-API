package com.kainos.ea.controller;
import com.kainos.ea.dao.CapabilitiesDao;
import com.kainos.ea.dao.JobFamilyDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.FailedToGetJobFamilyException;
import com.kainos.ea.exception.JobFamilyNameException;
import com.kainos.ea.model.JobFamilyRequest;
import com.kainos.ea.service.CapabilitiesService;
import com.kainos.ea.service.JobFamilyService;
import com.kainos.ea.util.DatabaseConnector;
import com.kainos.ea.validator.JobFamilyValidator;
import io.swagger.annotations.Api;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("API Hack Street Boys")
@Path("/api")

public class JobFamilyController {

    private static JobFamilyService jobFamilyService;

    public JobFamilyController()
    {
        jobFamilyService = new JobFamilyService(new JobFamilyDao(), new DatabaseConnector());
    }
    JobFamilyValidator jobFamilyValidator = new JobFamilyValidator();

    public JobFamilyController(JobFamilyService jobFamilyService) {this.jobFamilyService = jobFamilyService;}

    @POST
    @Path("/job-family")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createJobFamily(JobFamilyRequest jobFamilyRequest) throws DatabaseConnectionException, SQLException, JobFamilyNameException {
        try {
            if (jobFamilyValidator.isValidJobFamily(jobFamilyRequest)) {
                try {
                    int id = jobFamilyService.createJobFamily(jobFamilyRequest);
                    return Response.status(HttpStatus.CREATED_201).entity(id).build();
                } catch (DatabaseConnectionException | SQLException e) {
                    System.out.println(e);
                    return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
                }
            } else {
                return Response.status(HttpStatus.BAD_REQUEST_400).build();
            }
        } catch (JobFamilyNameException e) {
            return Response.status(HttpStatus.BAD_REQUEST_400).build();
        }
    }

    @GET
    @Path("/job-family")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobFamilies()
    {
        try
        {
            return Response.ok(jobFamilyService.getJobFamilies()).build();
        } catch (FailedToGetJobFamilyException e)
        {
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        }
    }
}
