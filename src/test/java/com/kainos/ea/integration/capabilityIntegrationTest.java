package com.kainos.ea.integration;

import com.kainos.ea.WebServiceApplication;
import com.kainos.ea.WebServiceConfiguration;
import com.kainos.ea.controller.CapabilitiesController;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.Capabilities;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class capabilityIntegrationTest {

    static final DropwizardAppExtension<WebServiceConfiguration> APP = new DropwizardAppExtension<>(
            WebServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void getCapabilities_shouldReturnListOfCapabilities() {
        List<Capabilities> response = APP.client().target("http://localhost:8080/api/capabilities")
                .request()
                .get(List.class);

        Assertions.assertTrue(response.size() > 0);
    }

    @Test
    void getCapabilities_shouldReturn200WhenValid() {
        Response response = APP.client().target("http://localhost:8080/api/capabilities")
                .request()
                .get();

        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void check_for_SQL_Exception() throws SQLException, DatabaseConnectionException {
        Response response = APP.client().target("http://localhost:8080/api/capabilities").request().get();

        Assertions.assertEquals(500, response.getStatus());
    }

}