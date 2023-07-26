package com.kainos.ea.service;
import com.kainos.ea.dao.JobRoleDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.FailedToCreateJobRoleException;
import com.kainos.ea.exception.InvalidJobRoleException;
import com.kainos.ea.model.JobRole;
import com.kainos.ea.model.JobRoleRequest;
import com.kainos.ea.util.DatabaseConnector;
import com.kainos.ea.validator.JobRoleValidator;
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
    JobRoleValidator jobRoleValidator = Mockito.mock(JobRoleValidator.class);
    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);

    JobRoleService jobRoleService = new JobRoleService(jobRoleDao,databaseConnector,jobRoleValidator);

    Connection conn;

    JobRoleRequest jobRoleRequest = new JobRoleRequest(
            "Software Engineer",
            1,
            1
    );

    @Test
    void getJobRole_Should_Return_Arraylist() throws DatabaseConnectionException, SQLException
    {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        ArrayList<JobRole> empList = new ArrayList<>();
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

    @Test
    void createRole_shouldReturnAnId_whenDaoReturnsAnId() throws SQLException, DatabaseConnectionException, InvalidJobRoleException, FailedToCreateJobRoleException {
        int expectedResult = 1;
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobRoleDao.createRole(jobRoleRequest, conn)).thenReturn(expectedResult);

        int result = jobRoleService.createRole(jobRoleRequest);

        assertEquals(result, expectedResult);
    }

    @Test
    void createRole_shouldThrowFailedToCreateJobRoleException_whenDaoThrowsSqlException() throws SQLException, DatabaseConnectionException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobRoleDao.createRole(jobRoleRequest, conn)).thenThrow(SQLException.class);

        assertThrows(FailedToCreateJobRoleException.class,
                () -> jobRoleService.createRole(jobRoleRequest));
    }

    @Test
    void  createRole_shouldThrowFailedToCreateJobRoleException_whenValidatorReturnsNotNull() {
        Mockito.when(jobRoleValidator.isValidJobRole(jobRoleRequest)).thenReturn("Test");

        assertThrows(InvalidJobRoleException.class,
                () -> jobRoleService.createRole(jobRoleRequest));
    }
}