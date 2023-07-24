package com.kainos.ea.service;

import com.kainos.ea.dao.CapabilitiesDao;
import com.kainos.ea.dao.JobFamilyDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.Capabilities;
import com.kainos.ea.model.JobFamily;
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
class JobFamilyServiceTest {

    JobFamilyDao jobFamilyDao = Mockito.mock(JobFamilyDao.class);
    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);

    JobFamilyService jobFamilyService = new JobFamilyService(jobFamilyDao,databaseConnector);

    Connection conn;

    @Test
    void getCapabilities_Should_Return_Arraylist() throws DatabaseConnectionException, SQLException
    {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        ArrayList<JobFamily> jobFamilyList = new ArrayList<>();
        Mockito.when(jobFamilyDao.getJobFamilies(conn)).thenReturn(jobFamilyList);

        assertEquals(jobFamilyList, jobFamilyService.getJobFamilies());
    }

    @Test
    void getCapabilities_checkSQLException() throws DatabaseConnectionException, SQLException
    {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(capabilitiesDao.getCapabilities(conn)).thenThrow(SQLException.class);
        assertThrows(SQLException.class,
                () -> capabilitiesService.getCapabilities());
    }

}