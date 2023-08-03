package com.kainos.ea.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.kainos.ea.WebServiceApplication;
import com.kainos.ea.WebServiceConfiguration;
import com.kainos.ea.exception.FailedToGetJobFamilyException;
import com.kainos.ea.service.JobFamilyService;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import java.util.ArrayList;
import java.util.List;
import com.kainos.ea.model.JobFamily;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

@ExtendWith(DropwizardExtensionsSupport.class)
class JobFamilyControllerTest {

    JobFamilyService jobFamilyService = Mockito.mock(JobFamilyService.class);

    JobFamilyController jobFamilyController = new JobFamilyController(jobFamilyService);

    static final DropwizardAppExtension<WebServiceConfiguration> APP = new DropwizardAppExtension<>(
        WebServiceApplication.class, null,
        new ResourceConfigurationSourceProvider()
    );

    @Test
    void check_for_FailedToGetJobFamilyException() throws FailedToGetJobFamilyException {
        Mockito.when(jobFamilyService.getJobFamilies())
            .thenThrow(new FailedToGetJobFamilyException());

        Response response = jobFamilyController.getJobFamilies();
        assertEquals(500, response.getStatus());
    }

    @Test
    void getAllBands_shouldReturnOk_whenServiceReturnsBandList() throws FailedToGetJobFamilyException {
        List<JobFamily> jobFamilyList = new ArrayList<>();
        Mockito.when(jobFamilyService.getJobFamilies()).thenReturn(jobFamilyList);

        Response result = jobFamilyController.getJobFamilies();
        assertEquals(200, result.getStatus());
    }

    @Test
    void getAllBands_shouldReturnServerError_whenServiceThrowsFailedToGetBandsException()
        throws FailedToGetJobFamilyException {
        Mockito.when(jobFamilyService.getJobFamilies()).thenThrow(FailedToGetJobFamilyException.class);

        Response result = jobFamilyController.getJobFamilies();
        assertEquals(500, result.getStatus());
    }
}
