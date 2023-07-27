package com.kainos.ea.controller;

import com.kainos.ea.WebServiceApplication;
import com.kainos.ea.WebServiceConfiguration;
import com.kainos.ea.exception.DatabaseConnectionException;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.sql.SQLException;

@ExtendWith(DropwizardExtensionsSupport.class)
public class JobRolesControllerTest
{
    JobRoleService jobRoleService = Mockito.mock(JobRoleService.class);

    JobRolesController jobRolesController = new JobRolesController(jobRoleService);

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

}
