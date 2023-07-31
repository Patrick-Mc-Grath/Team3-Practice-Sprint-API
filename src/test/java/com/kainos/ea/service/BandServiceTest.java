package com.kainos.ea.service;

import com.kainos.ea.dao.BandDao;
import com.kainos.ea.exception.BandDoesNotExistException;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.Band;
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
public class BandServiceTest {
    BandDao bandDao = Mockito.mock(BandDao.class);
    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);
    BandService bandService = new BandService(bandDao, databaseConnector);
    Connection conn;

    @Test
    void getBandById_shouldReturnBand_whenDaoReturnsBand() throws DatabaseConnectionException, SQLException, BandDoesNotExistException {
        int bandId = 1;
        Band expected = new Band(1, "Trainee");
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(bandDao.getBandById(bandId, conn)).thenReturn(expected);

        Band result = bandDao.getBandById(bandId, conn);

        assertEquals(expected, result);
    }

    @Test
    void getBandById_shouldThrowBandDoesNotExistException_whenDaoReturnsNull() throws DatabaseConnectionException, SQLException, BandDoesNotExistException {
        int bandId = 1;
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(bandDao.getBandById(bandId, conn)).thenReturn(null);

        assertThrows(BandDoesNotExistException.class,
                () -> bandService.getBandById(bandId));
    }

    @Test
    void getBandById_shouldThrowSqlException_whenDaoThrowsSqlException() throws DatabaseConnectionException, SQLException, BandDoesNotExistException {
        int bandId = 1;
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(bandDao.getBandById(bandId, conn)).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> bandService.getBandById(bandId));
    }

    @Test
    void getBandById_shouldThrowDatabaseConnectionException_whenDatabaseConnectorThrowsDatabaseConnectionException() throws DatabaseConnectionException, SQLException, BandDoesNotExistException {
        int bandId = 1;
        Mockito.when(databaseConnector.getConnection()).thenThrow(DatabaseConnectionException.class);

        assertThrows(DatabaseConnectionException.class,
                () -> bandService.getBandById(bandId));
    }
}
