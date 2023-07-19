package com.kainos.ea.integration;

import com.kainos.ea.WebServiceApplication;
import com.kainos.ea.WebServiceConfiguration;
import com.kainos.ea.model.RoleBandResponse;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class RoleBandIntegrationTest {
    static final DropwizardAppExtension<WebServiceConfiguration> APP = new DropwizardAppExtension<>(
            WebServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void getRoleBands_shouldReturnListOfRoleBandResponses() {
        List<RoleBandResponse> response = APP.client().target("http://localhost:8080/api/role-band-levels")
                .request()
                .get(List.class);

        Assertions.assertTrue(response.size() > 0);
    }
}
