package com.kainos.ea.controller;

import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.DescriptionLengthException;
import com.kainos.ea.exception.NameLengthException;
import com.kainos.ea.model.CapabilityRequest;
import com.kainos.ea.service.CapabilityService;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CapabilityControllerTest {

    CapabilityService capabilityService = Mockito.mock(CapabilityService.class);
    CapabilityController capabilityController = new CapabilityController(capabilityService);
    CapabilityRequest capabilityRequest = new CapabilityRequest("Test", "Testing");

    @Test
    void createCapability_shouldReturnOk_whenCapabilityIsCreated() throws DatabaseConnectionException, SQLException, NameLengthException, DescriptionLengthException {
        int testID = 23;
        Mockito.when(capabilityService.insertCapability(capabilityRequest)).thenReturn(testID);
        int expected = Response.status(HttpStatus.CREATED_201).build().getStatus();
        int actual = capabilityController.createCapability(capabilityRequest).getStatus();

        assertEquals(expected, actual);
    }

    @Test
    void createCapability_shouldReturn400_whenNameLengthException() throws DatabaseConnectionException, SQLException, NameLengthException, DescriptionLengthException {
        int expected = Response.status(HttpStatus.BAD_REQUEST_400).build().getStatus();
        Mockito.when(capabilityService.insertCapability(capabilityRequest)).thenThrow(NameLengthException.class);
        int actual = capabilityController.createCapability(capabilityRequest).getStatus();

        assertEquals(expected, actual);
    }

    @Test
    void createCapability_shouldReturn400_whenDescriptionLengthException() throws DatabaseConnectionException, SQLException, NameLengthException, DescriptionLengthException {
        int expected = Response.status(HttpStatus.BAD_REQUEST_400).build().getStatus();
        Mockito.when(capabilityService.insertCapability(capabilityRequest)).thenThrow(DescriptionLengthException.class);
        int actual = capabilityController.createCapability(capabilityRequest).getStatus();

        assertEquals(expected, actual);
    }

    @Test
    void createCapability_shouldReturn500_whenServiceThrowsDatabaseConnectionException() throws DatabaseConnectionException, SQLException, NameLengthException, DescriptionLengthException {
        int expected = 500;
        Mockito.when(capabilityService.insertCapability(capabilityRequest)).thenThrow(DatabaseConnectionException.class);
        int actual = capabilityController.createCapability(capabilityRequest).getStatus();

        assertEquals(expected, actual);
    }

    @Test
    void createCapability_shouldReturn500_whenServiceThrowsSQLException() throws DatabaseConnectionException, SQLException, NameLengthException, DescriptionLengthException {
        int expected = 500;
        Mockito.when(capabilityService.insertCapability(capabilityRequest)).thenThrow(SQLException.class);
        int actual = capabilityController.createCapability(capabilityRequest).getStatus();

        assertEquals(expected, actual);
    }
}
