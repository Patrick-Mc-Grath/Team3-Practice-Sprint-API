package com.kainos.ea.controller;

import com.kainos.ea.dao.JobFamilyDao;
import com.kainos.ea.exception.FailedToGetJobFamilyException;
import com.kainos.ea.service.JobFamilyService;
import com.kainos.ea.util.DatabaseConnector;
import io.swagger.annotations.Api;
import org.eclipse.jetty.http.HttpStatus;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("API Hack Street Boys")
@Path("/api")

public class JobFamilyController {

    private static JobFamilyService jobFamilyService;

    public JobFamilyController()
    {
        jobFamilyService = new JobFamilyService(new JobFamilyDao(), new DatabaseConnector());
    }

    public JobFamilyController(JobFamilyService jobFamilyService) {this.jobFamilyService = jobFamilyService;}

    @GET
    @Path("/job-family")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobFamilies() {
        try {
            return Response.ok(jobFamilyService.getJobFamilies()).build();
        } catch (FailedToGetJobFamilyException e) {
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        }
    }
}
