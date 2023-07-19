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
    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);
    RoleBandService roleBandService = Mockito.mock(RoleBandService.class);
    RoleBandDao roleBandDao = Mockito.mock(RoleBandDao.class);
    RoleBandController roleBandController = new RoleBandController();
    Connection conn;

    @Test
    public void getRoleBands_shouldReturnOK_whenServiceReturnsRoleBandResponse() throws DatabaseConnectionException, SQLException, FailedToGetRoleBandsException {

        List<RoleBandResponse> roleBandList = new ArrayList<>();
        String expectedResult = Response.ok().entity(roleBandList).build().toString();
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(roleBandDao.getRoleBands()).thenReturn(roleBandList);
        Mockito.when(roleBandService.getRoleBands()).thenReturn(roleBandList);

        String result = roleBandController.getRoleBands().toString();

        assertEquals(expectedResult, result);
    }

    @Test
    public void getRoleBands_shouldReturnServerError_whenServiceThrowsFailedToGetRoleBandsException() throws SQLException, DatabaseConnectionException, FailedToGetRoleBandsException {
        String expectedResult = Response.serverError().entity("SQL error").build().toString();
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(roleBandDao.getRoleBands()).thenThrow(SQLException.class);
        Mockito.when(roleBandService.getRoleBands()).thenThrow(FailedToGetRoleBandsException.class);

        String result = roleBandController.getRoleBands().toString();

        assertEquals(expectedResult, result);
    }

    @Test
    public void getRoleBands_shouldReturnServerError_whenServiceThrowsDatabaseConnectionException() throws SQLException, DatabaseConnectionException, FailedToGetRoleBandsException {
        String expectedResult = Response.serverError().build().toString();
        Mockito.when(databaseConnector.getConnection()).thenThrow(DatabaseConnectionException.class);
        Mockito.when(roleBandDao.getRoleBands()).thenThrow(DatabaseConnectionException.class);
        Mockito.when(roleBandService.getRoleBands()).thenThrow(DatabaseConnectionException.class);

        String result = roleBandController.getRoleBands().toString();

        assertEquals(expectedResult, result);
    }
}
