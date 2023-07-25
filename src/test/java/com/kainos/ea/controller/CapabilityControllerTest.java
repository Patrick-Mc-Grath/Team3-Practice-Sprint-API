package com.kainos.ea.controller;

import com.kainos.ea.WebServiceApplication;
import com.kainos.ea.WebServiceConfiguration;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.service.CapabilitiesService;
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
class CapabilityControllerTest {

    CapabilitiesService capabilitiesService = Mockito.mock(CapabilitiesService.class);

    CapabilityController capabilityController = new CapabilityController(capabilitiesService);



    @Test
    void check_for_SQL_Exception() throws SQLException, DatabaseConnectionException
    {
        Mockito.when(capabilitiesService.getCapabilities()).thenThrow(new SQLException());

        Response response = capabilityController.getCapabilities();
        Assertions.assertEquals(500,response.getStatus());
    }

    @Test
    void check_for_Database_Connection_Exception() throws SQLException, DatabaseConnectionException
    {
        Mockito.when(capabilitiesService.getCapabilities()).thenThrow(new DatabaseConnectionException());
        Response response = capabilityController.getCapabilities();
        Assertions.assertEquals(500,response.getStatus());
    }
}