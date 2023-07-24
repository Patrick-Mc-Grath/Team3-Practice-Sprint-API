package com.kainos.ea.controller;
import com.kainos.ea.dao.JobRoleDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.FailedToCreateJobRoleException;
import com.kainos.ea.exception.InvalidJobRoleException;
import com.kainos.ea.model.JobRoleRequest;
import com.kainos.ea.service.JobRoleService;
import com.kainos.ea.util.DatabaseConnector;
import com.kainos.ea.validator.JobRoleValidator;
import io.swagger.annotations.Api;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Backend API Job Roles")
@Path("/api")
public class JobRolesController
{
    private JobRoleService jobRoleService;
    private JobRoleValidator jobRoleValidator;
    public JobRolesController()
    {
        jobRoleService = new JobRoleService(new JobRoleDao(), new DatabaseConnector());
        jobRoleValidator = new JobRoleValidator();
    }

    public JobRolesController(JobRoleService jobRoleService)
    {
        this.jobRoleService = jobRoleService;
    }

    @GET
    @Path("/job-roles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobRoles()
    {
        try
        {
            return Response.ok(jobRoleService.getJobRoles()).build();
        } catch (SQLException | DatabaseConnectionException e)
        {
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        }
    }

    @POST
    @Path("/job-roles")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRole(JobRoleRequest jobRoleRequest) {
            try {
                int id = jobRoleService.createRole(jobRoleRequest);
                return Response.status(HttpStatus.CREATED_201).entity(id).build();
            } catch (FailedToCreateJobRoleException | DatabaseConnectionException e) {
                return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
            } catch (InvalidJobRoleException e) {
                return Response.status(HttpStatus.BAD_REQUEST_400).build();
            }
    }
}