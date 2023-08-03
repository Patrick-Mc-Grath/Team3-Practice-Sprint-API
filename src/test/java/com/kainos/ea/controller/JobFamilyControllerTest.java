package com.kainos.ea.controller;

import com.kainos.ea.exception.FailedToGetJobFamiliesException;
import com.kainos.ea.model.JobFamily;
import com.kainos.ea.service.JobFamilyService;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(DropwizardExtensionsSupport.class)
public class JobFamilyControllerTest {
    JobFamilyService jobFamilyService = Mockito.mock(JobFamilyService.class);
    JobFamilyController jobFamilyController = new JobFamilyController(jobFamilyService);

    @Test
    void getAllBands_shouldReturnOk_whenServiceReturnsBandList() throws FailedToGetJobFamiliesException {
        List<JobFamily> jobFamilyList = new ArrayList<>();
        Mockito.when(jobFamilyService.getJobFamilies()).thenReturn(jobFamilyList);

        Response result = jobFamilyController.getJobFamilies();
        assertEquals(200, result.getStatus());
    }

    @Test
    void getAllBands_shouldReturnServerError_whenServiceThrowsFailedToGetBandsException() throws FailedToGetJobFamiliesException {
        Mockito.when(jobFamilyService.getJobFamilies()).thenThrow(FailedToGetJobFamiliesException.class);

        Response result = jobFamilyController.getJobFamilies();
        assertEquals(500, result.getStatus());
    }
}