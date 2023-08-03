package com.kainos.ea.service;

import com.kainos.ea.dao.BandDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.FailedToGetBandsException;
import com.kainos.ea.model.Band;
import com.kainos.ea.util.DatabaseConnector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BandServiceTest {
    BandDao bandDao = Mockito.mock(BandDao.class);
    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);
    BandService bandService = new BandService(bandDao, databaseConnector);
    Connection conn;

    @Test
    void getAllBands_shouldReturnBandList_whenDaoReturnsBandList() throws SQLException, FailedToGetBandsException {
        List<Band> expectedResult = new ArrayList<>();
        Mockito.when(bandDao.getAllBands(conn)).thenReturn(expectedResult);

        List<Band> result = bandService.getAllBands();
        assertEquals(expectedResult, result);
    }

    @Test
    void getAllBands_shouldReturnFailedToGetBandsException_whenDaoThrowsSQLException() throws SQLException {
        Mockito.when(bandDao.getAllBands(conn)).thenThrow(SQLException.class);

        assertThrows(FailedToGetBandsException.class,
                () -> bandService.getAllBands());
    }

    @Test
    void getAllBands_shouldReturnFailedToGetBandsException_whenDatabaseConnectorThrowsDatabaseConnectionException() throws DatabaseConnectionException, SQLException {
        Mockito.when(databaseConnector.getConnection()).thenThrow(DatabaseConnectionException.class);

        assertThrows(FailedToGetBandsException.class,
                () -> bandService.getAllBands());
    }
}
