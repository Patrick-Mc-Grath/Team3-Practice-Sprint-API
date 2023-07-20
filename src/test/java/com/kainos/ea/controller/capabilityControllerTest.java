package com.kainos.ea.controller;

import com.kainos.ea.WebServiceApplication;
import com.kainos.ea.WebServiceConfiguration;
import com.kainos.ea.dao.CapabilitiesDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.service.CapabilitiesService;
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
class capabilityControllerTest {

    CapabilitiesDao capabilitiesDao = Mockito.mock(CapabilitiesDao.class);

    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);

    CapabilitiesService capabilitiesService = new CapabilitiesService(capabilitiesDao,databaseConnector);

    Connection conn;

    CapabilitiesController capabilitiesController = Mockito.mock(CapabilitiesController.class);

    static final DropwizardAppExtension<WebServiceConfiguration> APP = new DropwizardAppExtension<>(
            WebServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void check_for_SQL_Exception() throws SQLException, DatabaseConnectionException
    {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(capabilitiesService.getCapabilities()).thenThrow(new SQLException());

        Response response = capabilitiesController.getCapabilities();
        Assertions.assertEquals(500,response.getStatus());
    }

    @Test
    void check_for_Database_Connection_Exception() throws SQLException, DatabaseConnectionException
    {
        Mockito.when(databaseConnector.getConnection()).thenThrow(DatabaseConnectionException.class);

        Response response = capabilitiesController.getCapabilities();
        Assertions.assertEquals(500,response.getStatus());
    }
}
