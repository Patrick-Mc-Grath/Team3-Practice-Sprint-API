package com.kainos.ea.controller;

import com.kainos.ea.dao.TrainingDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.FailedToGetTrainingException;
import com.kainos.ea.exception.TrainingDoesNotExistException;
import com.kainos.ea.model.TrainingRequest;
import com.kainos.ea.service.TrainingService;
import com.kainos.ea.util.DatabaseConnector;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TrainingControllerTest {

    TrainingDao trainingDao = Mockito.mock(TrainingDao.class);
    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);
    TrainingService trainingService = Mockito.mock(TrainingService.class);
    TrainingController trainingController = Mockito.mock(TrainingController.class);
    Connection conn;

    @Test
    void getTrainingByBand_shouldReturnOk_whenServiceReturnsListOfTrainingCourses() throws DatabaseConnectionException, SQLException, TrainingDoesNotExistException, FailedToGetTrainingException {
        int bandId = 1;
        List<TrainingRequest> trainingList = new ArrayList<>();

        int expected = Response.ok().entity(trainingList).build().getStatus();

        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(trainingDao.getTrainingByBand(bandId, conn)).thenReturn(trainingList);
        Mockito.when(trainingService.getTrainingByBand(bandId)).thenReturn(trainingList);

        int actual = trainingController.getTrainingByBand(bandId).getStatus();

        assertEquals(expected, actual);

    }

    @Test
    void getTrainingByBand_shouldReturn400_whenServiceThrowsTrainingDoesNotExistException() throws DatabaseConnectionException, SQLException, TrainingDoesNotExistException {
        int bandId = 1;
        List<TrainingRequest> trainingList = new ArrayList<>();
        int expected = Response.status(HttpStatus.BAD_REQUEST_400).build().getStatus();

        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(trainingDao.getTrainingByBand(bandId, conn)).thenReturn(trainingList);
        Mockito.when(trainingService.getTrainingByBand(bandId)).thenThrow(TrainingDoesNotExistException.class);

        int actual = trainingController.getTrainingByBand(bandId).getStatus();

        assertEquals(expected, actual);
    }
}