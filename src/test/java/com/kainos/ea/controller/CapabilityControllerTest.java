package com.kainos.ea.controller;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.CapabilityDescriptionLengthException;
import com.kainos.ea.exception.FailedToGetCapabilityException;
import com.kainos.ea.exception.CapabilityNameLengthException;
import com.kainos.ea.model.CapabilityRequest;
import com.kainos.ea.service.CapabilitiesService;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(DropwizardExtensionsSupport.class)
@ExtendWith(MockitoExtension.class)
public class CapabilityControllerTest {

    CapabilitiesService capabilitiesService = Mockito.mock(CapabilitiesService.class);
    CapabilityController capabilityController = new CapabilityController(capabilitiesService);
    CapabilityRequest capabilityRequest = new CapabilityRequest("Test", "Testing");

    @Test
    void createCapability_shouldReturnOk_whenCapabilityIsCreated() throws DatabaseConnectionException, SQLException, CapabilityNameLengthException, CapabilityDescriptionLengthException {
        int testID = 23;
        Mockito.when(capabilitiesService.insertCapability(capabilityRequest)).thenReturn(testID);
        int expected = Response.status(HttpStatus.CREATED_201).build().getStatus();
        int actual = capabilityController.createCapability(capabilityRequest).getStatus();

        assertEquals(expected, actual);
    }

    @Test
    void createCapability_shouldReturn400_whenNameLengthException() throws DatabaseConnectionException, SQLException, CapabilityNameLengthException, CapabilityDescriptionLengthException {
        int expected = Response.status(HttpStatus.BAD_REQUEST_400).build().getStatus();
        Mockito.when(capabilitiesService.insertCapability(capabilityRequest)).thenThrow(CapabilityNameLengthException.class);
        int actual = capabilityController.createCapability(capabilityRequest).getStatus();

        assertEquals(expected, actual);
    }

    @Test
    void createCapability_shouldReturn400_whenDescriptionLengthException() throws DatabaseConnectionException, SQLException, CapabilityNameLengthException, CapabilityDescriptionLengthException {
        int expected = Response.status(HttpStatus.BAD_REQUEST_400).build().getStatus();
        Mockito.when(capabilitiesService.insertCapability(capabilityRequest)).thenThrow(CapabilityDescriptionLengthException.class);
        int actual = capabilityController.createCapability(capabilityRequest).getStatus();

        assertEquals(expected, actual);
    }

    @Test
    void createCapability_shouldReturn500_whenServiceThrowsDatabaseConnectionException() throws DatabaseConnectionException, SQLException, CapabilityNameLengthException, CapabilityDescriptionLengthException {
        int expected = 500;
        Mockito.when(capabilitiesService.insertCapability(capabilityRequest)).thenThrow(DatabaseConnectionException.class);
        int actual = capabilityController.createCapability(capabilityRequest).getStatus();

        assertEquals(expected, actual);
    }

    @Test
    void createCapability_shouldReturn500_whenServiceThrowsSQLException() throws DatabaseConnectionException, SQLException, CapabilityNameLengthException, CapabilityDescriptionLengthException {
        int expected = 500;
        Mockito.when(capabilitiesService.insertCapability(capabilityRequest)).thenThrow(SQLException.class);
        int actual = capabilityController.createCapability(capabilityRequest).getStatus();

        assertEquals(expected, actual);
    }

    @Test
    void check_for_SQL_Exception() throws FailedToGetCapabilityException
    {
        Mockito.when(capabilitiesService.getCapabilities()).thenThrow(new FailedToGetCapabilityException());

        Response response = capabilityController.getCapabilities();
        assertEquals(500,response.getStatus());
    }
}

