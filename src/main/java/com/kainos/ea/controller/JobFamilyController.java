package com.kainos.ea.controller;

import com.kainos.ea.dao.JobFamilyDao;
import com.kainos.ea.exception.FailedToGetJobFamiliesException;
import com.kainos.ea.service.JobFamilyService;
import com.kainos.ea.util.DatabaseConnector;
import io.swagger.annotations.Api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Backend API Job Families")
@Path("/api")
public class JobFamilyController {
    private JobFamilyService jobFamilyService;

    public JobFamilyController() {
        this.jobFamilyService = new JobFamilyService(new JobFamilyDao(), new DatabaseConnector());
    }

    public JobFamilyController(JobFamilyService jobFamilyService) {
        this.jobFamilyService = jobFamilyService;
    }

    @GET
    @Path("/job-families")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobFamilies(){
        try {
            return Response.ok(jobFamilyService.getJobFamilies()).build();
        } catch (FailedToGetJobFamiliesException e) {
            return Response.serverError().build();
        }
    }
}
