package com.kainos.ea.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kainos.ea.WebServiceApplication;
import com.kainos.ea.WebServiceConfiguration;
import com.kainos.ea.model.TrainingRequest;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import javax.ws.rs.client.Invocation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import javax.ws.rs.core.Response;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class TrainingIntegrationTest {
    static final DropwizardAppExtension<WebServiceConfiguration> APP = new DropwizardAppExtension<>(
            WebServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void getTrainingByBand_shouldReturnListOfTrainingCourses() {
        int id = 1;
        ObjectMapper mapper = new ObjectMapper();
        Invocation.Builder response = APP.client().target("http://localhost:8080/api/training/" + id)
            .request();

        List<TrainingRequest> trainingRequests = response.get(List.class);

        List<TrainingRequest> pojos = mapper.convertValue(trainingRequests, new TypeReference<List<TrainingRequest>>() { });

        Assertions.assertTrue(pojos.size() > 0);
        Assertions.assertEquals("Test professional skills training", pojos.get(0).getName());
        Assertions.assertEquals(200, response.get().getStatus());
    }

    @Test
    void getTrainingByBand_shouldReturn400_whenIdDoesNotExist() {
        int id = 123456;
        Response response = APP.client().target("http://localhost:8080/api/training/" + id).request().get(Response.class);

        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void getTrainingCategories_shouldReturnListOfCategories() {
        List<String> response = APP.client().target("http://localhost:8080/api/training-categories").request().get(List.class);

        Assertions.assertTrue(response.size() > 0);
    }
}
