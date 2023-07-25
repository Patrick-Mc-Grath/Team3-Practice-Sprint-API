package com.kainos.ea.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kainos.ea.WebServiceApplication;
import com.kainos.ea.WebServiceConfiguration;
import com.kainos.ea.model.JobRole;
import com.kainos.ea.model.RoleBandResponse;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.client.Invocation;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class RoleBandIntegrationTest {
    static final DropwizardAppExtension<WebServiceConfiguration> APP = new DropwizardAppExtension<>(
            WebServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void getRoleBands_shouldReturnListOfRoleBandResponses() {
        ObjectMapper mapper = new ObjectMapper();
        Invocation.Builder response = APP.client().target("http://localhost:8080/api/role-band-levels")
                .request();

        List<RoleBandResponse> roleBands = response.get(List.class);
        List<RoleBandResponse> pojos = mapper.convertValue(roleBands, new TypeReference<List<RoleBandResponse>>() { });

        Assertions.assertTrue(roleBands.size() > 0);
        Assertions.assertEquals(1, pojos.get(0).getRoleID());
        Assertions.assertEquals(1, pojos.get(0).getBandID());
        Assertions.assertEquals(200, response.get().getStatus());
    }
}
