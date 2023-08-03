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
import java.sql.SQLException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.jetty.http.HttpStatus;

/**
 * Controller for Job Roles.
 */
@Api("Backend API Job Roles")
@Path("/api")
public class JobRolesController
{
    private JobRoleService jobRoleService;
    public JobRolesController()
    {
        jobRoleService = new JobRoleService(new JobRoleDao(), new DatabaseConnector(), new JobRoleValidator());
    }

  public JobRolesController(JobRoleService jobRoleService) {
    this.jobRoleService = jobRoleService;
  }

  /**
   * Gets all job roles.
   *
   * @return Response
   */
  @GET
  @Path("/job-roles")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getJobRoles() {
    try {
      return Response.ok(jobRoleService.getJobRoles()).build();
    } catch (SQLException | DatabaseConnectionException e) {
      return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
    }
  }

    @POST
    @Path("/job-roles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRole (JobRoleRequest jobRoleRequest){
      try {
        int id = jobRoleService.createRole(jobRoleRequest);
        return Response.status(HttpStatus.CREATED_201).entity(id).build();
      } catch (FailedToCreateJobRoleException e) {
        System.err.println(e.getMessage());
        return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
      } catch (InvalidJobRoleException e) {
        System.err.println(e);
        return Response.status(HttpStatus.BAD_REQUEST_400).build();
      }
    }
}