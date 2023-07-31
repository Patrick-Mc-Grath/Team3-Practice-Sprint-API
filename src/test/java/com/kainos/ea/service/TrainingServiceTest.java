package com.kainos.ea.service;

import com.kainos.ea.dao.TrainingDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.TrainingDoesNotExistException;
import com.kainos.ea.model.TrainingRequest;
import com.kainos.ea.util.DatabaseConnector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class TrainingServiceTest {
    TrainingDao trainingDao = Mockito.mock(TrainingDao.class);
    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);
    TrainingService trainingService = new TrainingService(trainingDao, databaseConnector);
    Connection conn;

    @Test
    void getTrainingByBand_shouldReturnTrainingCourses_whenDaoReturnsTrainingCourses() throws DatabaseConnectionException, SQLException {
        int bandId = 1;
        List<TrainingRequest> expected = new ArrayList<>();
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(trainingDao.getTrainingByBand(bandId, conn)).thenReturn(expected);

        List<TrainingRequest> result = trainingDao.getTrainingByBand(bandId, conn);

        assertEquals(expected, result);
    }

    @Test
    void getTrainingByBand_shouldThrowTrainingDoesNotExistException_whenDaoReturnsEmpty() throws DatabaseConnectionException, SQLException {
        int bandId = 1;
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(trainingDao.getTrainingByBand(bandId, conn)).thenReturn(Collections.emptyList());

        assertThrows(TrainingDoesNotExistException.class,
                () -> trainingService.getTrainingByBand(bandId));
    }

    @Test
    void getTrainingByBand_shouldThrowSqlException_whenDaoThrowsSqlException() throws DatabaseConnectionException, SQLException {
        int bandId = 1;
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(trainingDao.getTrainingByBand(bandId, conn)).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> trainingService.getTrainingByBand(bandId));
    }

    @Test
    void getTrainingCategories_shouldReturnCategories_whenDaoReturnsCategories() throws DatabaseConnectionException, SQLException {
        List<String> expected = new ArrayList<>();
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(trainingDao.getTrainingCategories(conn)).thenReturn(expected);

        List<String> actual = trainingDao.getTrainingCategories(conn);

        assertEquals(expected, actual);
    }

    @Test
    void getTrainingCategories_shouldThrowSqlException_whenDaoThrowsSqlException() throws DatabaseConnectionException, SQLException{
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(trainingDao.getTrainingCategories(conn)).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> trainingService.getTrainingCategories());
    }

}
