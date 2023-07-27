package com.kainos.ea.service;

import com.kainos.ea.dao.CapabilitiesDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.DescriptionLengthException;
import com.kainos.ea.exception.NameLengthException;
import com.kainos.ea.model.CapabilityRequest;
import com.kainos.ea.util.DatabaseConnector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class CapabilityServiceTest {

    CapabilitiesDao capDao = Mockito.mock(CapabilitiesDao.class);
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

}
