package com.kainos.ea.controller;

import com.kainos.ea.WebServiceApplication;
import com.kainos.ea.WebServiceConfiguration;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.FailedToCreateJobRoleException;
import com.kainos.ea.exception.InvalidJobRoleException;
import com.kainos.ea.model.JobRoleRequest;
import com.kainos.ea.service.JobRoleService;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(DropwizardExtensionsSupport.class)
public class JobRolesControllerTest
{
    JobRoleService jobRoleService = Mockito.mock(JobRoleService.class);
    JobRolesController jobRolesController = new JobRolesController(jobRoleService);
    JobRoleRequest jobRoleRequest = new JobRoleRequest(
            "Software Engineer",
            1,
            1
    );

    static final DropwizardAppExtension<WebServiceConfiguration> APP = new DropwizardAppExtension<>(
            WebServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );


    @Test
    void check_for_SQL_Exception() throws SQLException, DatabaseConnectionException
    {
        Mockito.when(jobRoleService.getJobRoles()).thenThrow(new SQLException());

        Response response = jobRolesController.getJobRoles();
        assertEquals(500,response.getStatus());
    }

    @Test
    void check_for_Database_Connection_Exception() throws SQLException, DatabaseConnectionException
    {
        Mockito.when(jobRoleService.getJobRoles()).thenThrow(DatabaseConnectionException.class);

        Response response = jobRolesController.getJobRoles();
        assertEquals(500,response.getStatus());
    }

    @Test
    void createRole_shouldReturnCreated_whenServiceReturnsNewJobRoleId() throws InvalidJobRoleException, FailedToCreateJobRoleException {
        int expected = Response.status(HttpStatus.CREATED_201).build().getStatus();
        Mockito.when(jobRoleService.createRole(jobRoleRequest)).thenReturn(1);
        int actual = jobRolesController.createRole(jobRoleRequest).getStatus();

        assertEquals(expected, actual);
    }

    @Test
    void createRole_shouldReturnServerError_whenServiceThrowsFailedToCreateJobRoleException() throws InvalidJobRoleException, FailedToCreateJobRoleException {
        int expected = Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build().getStatus();
        Mockito.when(jobRoleService.createRole(jobRoleRequest)).thenThrow(FailedToCreateJobRoleException.class);
        int actual = jobRolesController.createRole(jobRoleRequest).getStatus();

        assertEquals(expected, actual);
    }

    @Test
    void createRole_shouldReturnBadRequest_whenServiceThrowsInvalidJobRoleException() throws InvalidJobRoleException, FailedToCreateJobRoleException {
        int expected = Response.status(HttpStatus.BAD_REQUEST_400).build().getStatus();
        Mockito.when(jobRoleService.createRole(jobRoleRequest)).thenThrow(InvalidJobRoleException.class);
        int actual = jobRolesController.createRole(jobRoleRequest).getStatus();

        assertEquals(expected, actual);
    }

}
