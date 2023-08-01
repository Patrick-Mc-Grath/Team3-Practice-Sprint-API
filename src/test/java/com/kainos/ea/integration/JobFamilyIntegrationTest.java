package com.kainos.ea.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kainos.ea.WebServiceApplication;
import com.kainos.ea.WebServiceConfiguration;
import com.kainos.ea.model.JobFamily;
import com.kainos.ea.model.JobFamilyRequest;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class JobFamilyIntegrationTest {

    static final DropwizardAppExtension<WebServiceConfiguration> APP = new DropwizardAppExtension<>(
            WebServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void createJobFamily_shouldReturnIdOfJobFamily() {
        JobFamilyRequest jobFamilyRequest = new JobFamilyRequest(1, "test");

        int response = APP.client().target("http://localhost:8080/api/job-family")
                .request()
                .post(Entity.entity(jobFamilyRequest, MediaType.APPLICATION_JSON_TYPE))
                .readEntity(Integer.class);

        Assertions.assertNotNull(response);
    }

    @Test
    void createJobFamily_shouldReturn400Error_whenNameExceptionTooLong() {
        JobFamilyRequest jobFamilyRequest = new JobFamilyRequest( 1, "test".repeat(50));

        Response response = APP.client().target("http://localhost:8080/api/job-family")
                .request()
                .post(Entity.entity(jobFamilyRequest, MediaType.APPLICATION_JSON_TYPE));

        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void createJobFamily_shouldReturn400Error_whenNameExceptionNull() {
        JobFamilyRequest jobFamilyRequest = new JobFamilyRequest( 1, "");

        Response response = APP.client().target("http://localhost:8080/api/job-family")
                .request()
                .post(Entity.entity(jobFamilyRequest, MediaType.APPLICATION_JSON_TYPE));

        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void getJobFamily_shouldReturnListOfCapabilities() {
        ObjectMapper mapper = new ObjectMapper();
        Invocation.Builder response = APP.client().target("http://localhost:8080/api/job-family")
                .request();

        List<JobFamily> jobFamilies = response.get(List.class);
        List<JobFamily> pojos = mapper.convertValue(jobFamilies, new TypeReference<List<JobFamily>>() { });

        Assertions.assertTrue(jobFamilies.size() > 0);
        Assertions.assertEquals("Engineering Strategy and Planning", pojos.get(0).getName());
        Assertions.assertEquals(200, response.get().getStatus());
    }
}