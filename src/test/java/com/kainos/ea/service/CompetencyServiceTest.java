package com.kainos.ea.service;

import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.CompetencyRequest;
import com.kainos.ea.exception.FailedToGetCompsException;
import com.kainos.ea.dao.CompetencyDao;
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

@ExtendWith(MockitoExtension.class)
public class CompetencyServiceTest {

    CompetencyDao compDao = Mockito.mock(CompetencyDao.class);
    DatabaseConnector dbConnector = Mockito.mock(DatabaseConnector.class);
    CompetencyService compService = new CompetencyService(compDao, dbConnector);
    List<CompetencyRequest> comps = new ArrayList<>();
    Connection connection;
    int bandId = 1;

    @Test
    void getCompsWithBand_shouldReturnAListOfComps_whenDaoReturnsListOfComps() throws SQLException, DatabaseConnectionException, FailedToGetCompsException {
        Mockito.when(dbConnector.getConnection()).thenReturn(connection);
        Mockito.when(compDao.getAllCompsAndBand(bandId, connection)).thenReturn(comps);

        assertEquals(comps, compService.getAllCompsWithBand(bandId));
    }


    @Test
    void getCompsWithBand_shouldThrowFailedToGetCompsException_whenDaoThrowsSQLException() throws SQLException, DatabaseConnectionException {
        Mockito.when(dbConnector.getConnection()).thenReturn(connection);
        Mockito.when(compDao.getAllCompsAndBand(bandId, connection)).thenThrow(SQLException.class);

        assertThrows(FailedToGetCompsException.class,
                () -> compService.getAllCompsWithBand(bandId));
    }


}
