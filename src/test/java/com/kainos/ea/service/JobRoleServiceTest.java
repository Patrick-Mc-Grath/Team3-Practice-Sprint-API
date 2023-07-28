package com.kainos.ea.service;
import com.kainos.ea.dao.JobRoleDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.JobRole;
import com.kainos.ea.model.JobRoleResponse;
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
class JobRoleServiceTest {

    JobRoleDao jobRoleDao = Mockito.mock(JobRoleDao.class);
    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);

    JobRoleService jobRoleService = new JobRoleService(jobRoleDao,databaseConnector);

    Connection conn;

    @Test
    void getJobRole_Should_Return_Arraylist() throws DatabaseConnectionException, SQLException
    {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        ArrayList<JobRoleResponse> empList = new ArrayList<>();
        Mockito.when(jobRoleDao.getRoles(conn)).thenReturn(empList);

        assertEquals(empList, jobRoleService.getJobRoles());
    }

    @Test
    void getJobRole_checkSQLException() throws DatabaseConnectionException, SQLException
    {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobRoleDao.getRoles(conn)).thenThrow(SQLException.class);
        assertThrows(SQLException.class,
                () -> jobRoleService.getJobRoles());
    }

    @Test
    void checkDatabaseConnectionException() throws DatabaseConnectionException, SQLException
    {
        Mockito.when(databaseConnector.getConnection()).thenThrow(DatabaseConnectionException.class);
        assertThrows(DatabaseConnectionException.class,
                () -> jobRoleService.getJobRoles());
    }

}