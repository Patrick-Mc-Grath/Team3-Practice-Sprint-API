package com.kainos.ea.controller;

import com.kainos.ea.WebServiceApplication;
import com.kainos.ea.WebServiceConfiguration;
import com.kainos.ea.dao.JobRoleDao;
import com.kainos.ea.exception.DatabaseConnectionException;
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
    JobRoleDao jobRoleDao = Mockito.mock(JobRoleDao.class);
    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);

    JobRolesController jobRolesController = new JobRolesController();

    JobRoleService jobRoleService = new JobRoleService(jobRoleDao,databaseConnector);

    Connection conn;

    static final DropwizardAppExtension<WebServiceConfiguration> APP = new DropwizardAppExtension<>(
            WebServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void check_for_SQL_Exception() throws SQLException, DatabaseConnectionException
    {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobRoleService.getJobRoles()).thenThrow(new SQLException());

        Response response = jobRolesController.getJobRoles();
        Assertions.assertEquals(500,response.getStatus());
    }

    @Test
    void check_for_Database_Connection_Exception() throws SQLException, DatabaseConnectionException
    {
        Mockito.when(databaseConnector.getConnection()).thenThrow(DatabaseConnectionException.class);

        Response response = jobRolesController.getJobRoles();
        Assertions.assertEquals(500,response.getStatus());
    }

}
