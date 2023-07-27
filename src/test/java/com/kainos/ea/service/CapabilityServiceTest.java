package com.kainos.ea.service;

import com.kainos.ea.dao.CapabilityDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.DescriptionLengthException;
import com.kainos.ea.exception.NameLengthException;
import com.kainos.ea.model.CapabilityRequest;
import com.kainos.ea.dao.CapabilitiesDao;
import com.kainos.ea.exception.FailedToGetCapabilityException;
import com.kainos.ea.model.Capabilities;
import com.kainos.ea.util.DatabaseConnector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class CapabilityServiceTest {

    CapabilityDao capDao = Mockito.mock(CapabilityDao.class);
    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);
    CapabilityService capService = new CapabilityService(capDao, databaseConnector);
    Connection conn;
    CapabilityRequest capRequest = new CapabilityRequest(
            "Test",
            "Testing"
    );

    @Test
    void insertCapability_shouldReturnId_whenDaoReturnsId() throws DatabaseConnectionException, SQLException, NameLengthException, DescriptionLengthException {
        int expectedResult = 1;
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(capDao.insertCapability(capRequest, conn)).thenReturn(expectedResult);

        int result = capService.insertCapability(capRequest);

        assertEquals(result, expectedResult);
    }

    @Test
    void insertCapability_shouldThrowSqlException_whenDaoThrowsSqlException() throws SQLException, DatabaseConnectionException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(capDao.insertCapability(capRequest, conn)).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> capService.insertCapability(capRequest));
    }



    CapabilitiesDao capabilitiesDao = Mockito.mock(CapabilitiesDao.class);
    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);

    CapabilitiesService capabilitiesService = new CapabilitiesService(capabilitiesDao,databaseConnector);

    Connection conn;

    @Test
    void getCapabilities_Should_Return_Arraylist() throws DatabaseConnectionException, SQLException, FailedToGetCapabilityException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        ArrayList<Capabilities> empList = new ArrayList<>();
        Mockito.when(capabilitiesDao.getCapabilities(conn)).thenReturn(empList);

        assertEquals(empList, capabilitiesService.getCapabilities());
    }

    @Test
    void getCapabilities_checkSQLException() throws DatabaseConnectionException, SQLException
    {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(capabilitiesDao.getCapabilities(conn)).thenThrow(SQLException.class);
        assertThrows(FailedToGetCapabilityException.class,
                () -> capabilitiesService.getCapabilities());
    }

    @Test
    void checkDatabaseConnectionException() throws DatabaseConnectionException, SQLException
    {
        Mockito.when(databaseConnector.getConnection()).thenThrow(DatabaseConnectionException.class);
        assertThrows(FailedToGetCapabilityException.class,
                () -> capabilitiesService.getCapabilities());
    }
}

