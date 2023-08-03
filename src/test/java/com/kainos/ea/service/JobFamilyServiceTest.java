package com.kainos.ea.service;

import com.kainos.ea.dao.JobFamilyDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.FailedToGetJobFamiliesException;
import com.kainos.ea.model.JobFamily;
import com.kainos.ea.util.DatabaseConnector;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JobFamilyServiceTest {
    JobFamilyDao jobFamilyDao = Mockito.mock(JobFamilyDao.class);
    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);
    JobFamilyService jobFamilyService = new JobFamilyService(jobFamilyDao, databaseConnector);
    Connection conn;

    @Test
    void getJobFamilies_shouldReturnBandList_whenDaoReturnsBandList() throws SQLException, FailedToGetJobFamiliesException {
        List<JobFamily> expectedResult = new ArrayList<>();
        Mockito.when(jobFamilyDao.getJobFamilies(conn)).thenReturn(expectedResult);

        List<JobFamily> result = jobFamilyService.getJobFamilies();
        assertEquals(expectedResult, result);
    }

    @Test
    void getJobFamilies_shouldReturnFailedToGetBandsException_whenDaoThrowsSQLException() throws SQLException {
        Mockito.when(jobFamilyDao.getJobFamilies(conn)).thenThrow(SQLException.class);

        assertThrows(FailedToGetJobFamiliesException.class,
                () -> jobFamilyService.getJobFamilies());
    }

    @Test
    void getJobFamilies_shouldReturnFailedToGetJobFamiliesException_whenDatabaseConnectorThrowsDatabaseConnectionException() throws DatabaseConnectionException, SQLException {
        Mockito.when(databaseConnector.getConnection()).thenThrow(DatabaseConnectionException.class);

        assertThrows(FailedToGetJobFamiliesException.class,
                () -> jobFamilyService.getJobFamilies());
    }
}
