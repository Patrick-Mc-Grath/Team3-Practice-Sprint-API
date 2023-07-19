package com.kainos.ea.service;

import com.kainos.ea.cli.CompetencyRequest;
import com.kainos.ea.client.DatabaseConnectionException;
import com.kainos.ea.client.FailedToGetCompsException;
import com.kainos.ea.db.CompetencyDao;
import com.kainos.ea.db.DatabaseConnector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static com.kainos.ea.db.DatabaseConnector.conn;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class CompetencyServiceTest {

    CompetencyDao compDao = Mockito.mock(CompetencyDao.class);
    DatabaseConnector dbConnector = Mockito.mock(DatabaseConnector.class);
    CompetencyService compService = new CompetencyService();
    List<CompetencyRequest> comps = new ArrayList<>();
    Connection connection;

    @Test
    void getCompsWithBand_shouldReturnAListOfComps_whenDaoReturnsListOfComps() throws SQLException, DatabaseConnectionException, FailedToGetCompsException {
        Mockito.when(dbConnector.getConnection()).thenReturn(connection);
        Mockito.when(compDao.getAllCompsAndBand(connection)).thenReturn(comps);

        assertEquals(comps, compService.getAllCompsWithBand());
    }


    @Test
    void getCompsWithBand_shouldThrowSQLException_whenDaoThrowsSQLException() throws SQLException, DatabaseConnectionException {
        Mockito.when(dbConnector.getConnection()).thenReturn(connection);
        Mockito.when(compDao.getAllCompsAndBand(connection)).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> compService.getAllCompsWithBand());
    }

    @Test
    void getAllCompsWithBand_shouldThrowDatabaseConnectionException_whenDaoThrowsDatabaseConnectionException() throws SQLException, DatabaseConnectionException {
        Mockito.when(dbConnector.getConnection()).thenReturn(conn);
        Mockito.when(compDao.getAllCompsAndBand(conn)).thenThrow(DatabaseConnectionException.class);

        assertThrows(FailedToGetCompsException.class,
                () -> compService.getAllCompsWithBand());
    }


}
