package com.kainos.ea.controller;

import com.kainos.ea.WebServiceApplication;
import com.kainos.ea.WebServiceConfiguration;
import com.kainos.ea.exception.BandDoesNotExistException;
import com.kainos.ea.exception.FailedToGetCompsException;
import com.kainos.ea.service.CompetencyService;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import javax.ws.rs.core.Response;
import java.sql.Connection;

@ExtendWith(DropwizardExtensionsSupport.class)
    public class CompetencyControllerTests {

        CompetencyService compService = Mockito.mock(CompetencyService.class);
        CompetencyController compController = new CompetencyController(compService);
        Connection conn;

        @Test
        void check_for_failedToGetCompsException() throws FailedToGetCompsException, BandDoesNotExistException {
            Mockito.when(compService.getAllCompsWithBand(1)).thenThrow(new FailedToGetCompsException());
            Response response = compController.getCompetenciesWithBand(1);
            Assertions.assertEquals(500,response.getStatus());
        }

    @Test
    void check_for_failedBandDoesNotExistException() throws FailedToGetCompsException, BandDoesNotExistException {
        Mockito.when(compService.getAllCompsWithBand(1234567)).thenThrow(new BandDoesNotExistException());
        Response response = compController.getCompetenciesWithBand(1234567);
        Assertions.assertEquals(400,response.getStatus());
    }

    }

