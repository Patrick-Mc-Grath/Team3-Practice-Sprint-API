package com.kainos.ea.controller;

import com.kainos.ea.dao.RoleBandDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.FailedToGetRoleBandsException;
import com.kainos.ea.model.Band;
import com.kainos.ea.model.RoleBandResponse;
import com.kainos.ea.service.RoleBandService;
import com.kainos.ea.util.DatabaseConnector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class RoleBandControllerTest {
    RoleBandService roleBandService = Mockito.mock(RoleBandService.class);
    RoleBandController roleBandController = new RoleBandController(roleBandService);


    @Test
    public void getRoleBands_shouldReturnOK_whenServiceReturnsRoleBandResponse() throws DatabaseConnectionException, SQLException, FailedToGetRoleBandsException {

        List<RoleBandResponse> roleBandList = new ArrayList<>();
        int expectedResult = 200;
        Mockito.when(roleBandService.getRoleBands()).thenReturn(roleBandList);

        Response result = roleBandController.getRoleBands();

        assertEquals(expectedResult, result.getStatus());
    }

    @Test
    public void getRoleBands_shouldReturnServerError_whenServiceThrowsFailedToGetRoleBandsException_orDatabaseConnectionException() throws DatabaseConnectionException, FailedToGetRoleBandsException {
        int expectedResult = 500;
        Mockito.when(roleBandService.getRoleBands()).thenThrow(FailedToGetRoleBandsException.class);

        Response result = roleBandController.getRoleBands();

        assertEquals(expectedResult, result.getStatus());
    }
}
