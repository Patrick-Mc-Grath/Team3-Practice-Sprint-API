package com.kainos.ea.service;

import com.kainos.ea.dao.RoleBandDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.FailedToGetRoleBandsException;
import com.kainos.ea.model.RoleBandResponse;
import com.kainos.ea.util.DatabaseConnector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class RoleBandServiceTest {
    RoleBandDao roleBandDao = Mockito.mock(RoleBandDao.class);
    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);
    RoleBandService roleBandService = new RoleBandService(roleBandDao, databaseConnector);

    Connection conn;

    @Test
    void getRoleBands_shouldReturnRoleBandResponseList_whenDaoReturnsRoleBandResponseList() throws DatabaseConnectionException, SQLException, FailedToGetRoleBandsException {
        List<RoleBandResponse> expectedResult = new ArrayList<>();
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(roleBandDao.getRoleBands(conn)).thenReturn(expectedResult);

        List<RoleBandResponse> result = roleBandService.getRoleBands();

        assertEquals(expectedResult, result);
    }

    @Test
    public void getRoleBands_shouldThrowFailedToGetRoleBandsException_whenDaoThrowsSQLException() throws SQLException, DatabaseConnectionException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(roleBandDao.getRoleBands(conn)).thenThrow(SQLException.class);

        assertThrows(FailedToGetRoleBandsException.class,
                () -> roleBandService.getRoleBands());
    }

    @Test
    public void getRoleBands_shouldThrowFailedToGetRoleBandsException_whenDaoThrowsDatabaseConnectionException() throws SQLException, DatabaseConnectionException {
        Mockito.when(databaseConnector.getConnection()).thenThrow(DatabaseConnectionException.class);

        assertThrows(FailedToGetRoleBandsException.class,
                () -> roleBandService.getRoleBands());
    }
}
