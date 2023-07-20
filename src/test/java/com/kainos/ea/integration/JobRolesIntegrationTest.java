package com.kainos.ea.integration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kainos.ea.WebServiceApplication;
import com.kainos.ea.WebServiceConfiguration;
import com.kainos.ea.model.JobRole;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class JobRolesIntegrationTest {

    static final DropwizardAppExtension<WebServiceConfiguration> APP = new DropwizardAppExtension<>(
            WebServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void getEmployees_shouldReturnListOfEmployees() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<JobRole> response = APP.client().target("http://localhost:8080/api/job-roles")
                .request()
                .get(List.class);


        Assertions.assertTrue(response.size() > 0);
        List<JobRole> pojos = mapper.convertValue(response, new TypeReference<List<JobRole>>() { });
        Assertions.assertEquals(200, APP.client().target("http://localhost:8080/api/job-roles")
                .request()
                .get().getStatus());
    }


}
