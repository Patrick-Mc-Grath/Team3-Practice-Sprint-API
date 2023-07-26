package com.kainos.ea.controller;

import com.kainos.ea.WebServiceApplication;
import com.kainos.ea.WebServiceConfiguration;
import com.kainos.ea.dao.JobRoleDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.FailedToCreateJobRoleException;
import com.kainos.ea.exception.InvalidJobRoleException;
import com.kainos.ea.model.JobRoleRequest;
import com.kainos.ea.service.JobRoleService;
import com.kainos.ea.util.DatabaseConnector;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.SQLException;

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
        Assertions.assertEquals(500,response.getStatus());
    }

    @Test
    void check_for_Database_Connection_Exception() throws SQLException, DatabaseConnectionException
    {
        Mockito.when(jobRoleService.getJobRoles()).thenThrow(DatabaseConnectionException.class);

        Response response = jobRolesController.getJobRoles();
        Assertions.assertEquals(500,response.getStatus());
    }

    @Test
    void createRole_shouldReturnCreated_whenServiceReturnsNewJobRoleId() throws InvalidJobRoleException, FailedToCreateJobRoleException {
        Mockito.when(jobRoleService.createRole(jobRoleRequest)).thenReturn(1);

        Response response = jobRolesController.getJobRoles();
        Assertions.assertEquals(201, response.getStatus());
    }

    @Test
    void createRole_shouldReturnServerError_whenServiceThrowsFailedToCreateJobRoleException() throws InvalidJobRoleException, FailedToCreateJobRoleException {
        Mockito.when(jobRoleService.createRole(jobRoleRequest)).thenThrow(FailedToCreateJobRoleException.class);

        Response response = jobRolesController.getJobRoles();
        Assertions.assertEquals(500, response.getStatus());
    }

    @Test
    void createRole_shouldReturnBadRequest_whenServiceThrowsInvalidJobRoleException() throws InvalidJobRoleException, FailedToCreateJobRoleException {
        Mockito.when(jobRoleService.createRole(jobRoleRequest)).thenThrow(InvalidJobRoleException.class);

        Response response = jobRolesController.getJobRoles();
        Assertions.assertEquals(400, response.getStatus());
    }

}
