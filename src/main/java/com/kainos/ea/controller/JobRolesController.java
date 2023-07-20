package com.kainos.ea.controller;
import com.kainos.ea.dao.JobRoleDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.service.JobRoleService;
import com.kainos.ea.util.DatabaseConnector;
import io.swagger.annotations.Api;
import org.eclipse.jetty.http.HttpStatus;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Backend API Job Roles")
@Path("/api")
public class JobRolesController
{
    private static JobRoleService jobRoleService;
    public JobRolesController()
    {
        jobRoleService = new JobRoleService(new JobRoleDao(),new DatabaseConnector());
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
}