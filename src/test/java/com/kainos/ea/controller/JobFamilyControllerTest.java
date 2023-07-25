package com.kainos.ea.controller;

import com.kainos.ea.WebServiceApplication;
import com.kainos.ea.WebServiceConfiguration;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.FailedToGetJobFamilyException;
import com.kainos.ea.service.CapabilitiesService;
import com.kainos.ea.service.JobFamilyService;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.sql.SQLException;

@ExtendWith(DropwizardExtensionsSupport.class)
class JobFamilyControllerTest {

    JobFamilyService jobFamilyService = Mockito.mock(JobFamilyService.class);

    JobFamilyController jobFamilyController = new JobFamilyController(jobFamilyService);

    static final DropwizardAppExtension<WebServiceConfiguration> APP = new DropwizardAppExtension<>(
            WebServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void check_for_SQL_Exception() throws FailedToGetJobFamilyException
    {
        Mockito.when(jobFamilyService.getJobFamilies()).thenThrow(new FailedToGetJobFamilyException());

        Response response = jobFamilyController.getJobFamilies();
        Assertions.assertEquals(500,response.getStatus());
    }
}