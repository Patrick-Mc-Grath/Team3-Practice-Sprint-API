package com.kainos.ea.controller;

import com.kainos.ea.WebServiceApplication;
import com.kainos.ea.WebServiceConfiguration;
import com.kainos.ea.client.DatabaseConnectionException;
import com.kainos.ea.client.FailedToGetCompsException;
import com.kainos.ea.controllers.CompetencyController;
import com.kainos.ea.db.CompetencyDao;
import com.kainos.ea.db.DatabaseConnector;
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
import java.sql.SQLException;

    @ExtendWith(DropwizardExtensionsSupport.class)
    public class CompetencyControllerTests
    {
        CompetencyDao compDao = Mockito.mock(CompetencyDao.class);
        DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);

        CompetencyController compController = new CompetencyController();

        CompetencyService compService = new CompetencyService(compDao, databaseConnector);

        Connection conn;

        static final DropwizardAppExtension<WebServiceConfiguration> APP = new DropwizardAppExtension<>(
                WebServiceApplication.class, null,
                new ResourceConfigurationSourceProvider()
        );

        @Test
        void check_for_SQL_Exception() throws SQLException, DatabaseConnectionException, FailedToGetCompsException {
            Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
            Mockito.when(compService.getAllCompsWithBand(1)).thenThrow(new SQLException());

            Response response = compController.getCompetenciesWithBand(1);
            Assertions.assertEquals(500,response.getStatus());
        }

        @Test
        void check_for_Database_Connection_Exception() throws SQLException, DatabaseConnectionException
        {
            Mockito.when(databaseConnector.getConnection()).thenThrow(DatabaseConnectionException.class);

            Response response = compController.getCompetenciesWithBand(1);
            Assertions.assertEquals(500,response.getStatus());
        }

    }

