package com.kainos.ea.controller;

import com.kainos.ea.WebServiceApplication;
import com.kainos.ea.WebServiceConfiguration;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.FailedToGetJobFamilyException;
import com.kainos.ea.exception.JobFamilyNameException;
import com.kainos.ea.model.JobFamilyRequest;
import com.kainos.ea.service.CapabilitiesService;
import com.kainos.ea.service.JobFamilyService;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(DropwizardExtensionsSupport.class)
@ExtendWith(MockitoExtension.class)
class JobFamilyControllerTest {

    JobFamilyService jobFamilyService = Mockito.mock(JobFamilyService.class);
    JobFamilyController jobFamilyController = new JobFamilyController(jobFamilyService);

    JobFamilyRequest jobFamilyRequest = new JobFamilyRequest(1, "Test");

    @Test
    void createJobFamily_shouldReturnOk_whenJobFamilyIsCreated() throws DatabaseConnectionException, SQLException, JobFamilyNameException {
        int testID = 25;
        Mockito.when(jobFamilyService.createJobFamily(jobFamilyRequest)).thenReturn(testID);
        int expected = Response.status(HttpStatus.CREATED_201).build().getStatus();
        int actual = jobFamilyController.createJobFamily(jobFamilyRequest).getStatus();

        assertEquals(expected, actual);
    }

    @Test
    void createJobFamily_shouldReturn400WhenNameException() throws DatabaseConnectionException, SQLException, JobFamilyNameException {
        int expected = Response.status(HttpStatus.BAD_REQUEST_400).build().getStatus();
        Mockito.when(jobFamilyService.createJobFamily(jobFamilyRequest)).thenThrow(JobFamilyNameException.class);
        int actual = jobFamilyController.createJobFamily(jobFamilyRequest).getStatus();

        assertEquals(expected, actual);
    }

    @Test
    void createJobFamily_shouldReturn500WhenDatabaseConnectionException() throws DatabaseConnectionException, SQLException, JobFamilyNameException {
        int expected = Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build().getStatus();
        Mockito.when(jobFamilyService.createJobFamily(jobFamilyRequest)).thenThrow(DatabaseConnectionException.class);
        int actual = jobFamilyController.createJobFamily(jobFamilyRequest).getStatus();

        assertEquals(expected, actual);
    }

    @Test
    void createJobFamily_shouldReturn500WhenSQLException() throws DatabaseConnectionException, SQLException, JobFamilyNameException {
        int expected = Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build().getStatus();
        Mockito.when(jobFamilyService.createJobFamily(jobFamilyRequest)).thenThrow(SQLException.class);
        int actual = jobFamilyController.createJobFamily(jobFamilyRequest).getStatus();

        assertEquals(expected, actual);
    }

    @Test
    void check_for_SQL_Exception() throws FailedToGetJobFamilyException
    {
        Mockito.when(jobFamilyService.getJobFamilies()).thenThrow(new FailedToGetJobFamilyException());

        Response response = jobFamilyController.getJobFamilies();
        assertEquals(500,response.getStatus());
    }
}