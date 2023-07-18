package com.kainos.ea.controller;

import com.kainos.ea.dao.EmployeeDao;
import com.kainos.ea.dao.JobRoleDao;
import com.kainos.ea.dao.SalesEmployeeDao;

import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.service.EmployeeService;
import com.kainos.ea.service.JobRoleService;
import com.kainos.ea.service.SalesEmployeeService;
import com.kainos.ea.util.DatabaseConnector;
import com.kainos.ea.validator.EmployeeValidator;
import io.swagger.annotations.Api;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.SQLException;

@Api("API for HR app")
@Path("/hr")
public class HR {
    private static EmployeeService employeeService;

    private static JobRoleService jobRoleService;

    public HR() {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        employeeService = new EmployeeService(new EmployeeDao(), databaseConnector);
        jobRoleService = new JobRoleService(new JobRoleDao(),databaseConnector);
    }

    @GET
    @Path("/employee")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployees() {
        try {
            return Response.ok(employeeService.getEmployees()).build();
        } catch (SQLException | DatabaseConnectionException e) {
            System.out.println(e);
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        }
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
