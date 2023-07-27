package com.kainos.ea.integration;

import com.kainos.ea.WebServiceApplication;
import com.kainos.ea.WebServiceConfiguration;
import com.kainos.ea.model.CapabilityRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kainos.ea.WebServiceApplication;
import com.kainos.ea.WebServiceConfiguration;
import com.kainos.ea.model.Capabilities;
import com.kainos.ea.model.JobRole;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;
import java.util.List;


@ExtendWith(DropwizardExtensionsSupport.class)
public class CapabilityIntegrationTest {

    static final DropwizardAppExtension<WebServiceConfiguration> APP = new DropwizardAppExtension<>(
            WebServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void postCapability_shouldReturnIdOfCapability() {
        CapabilityRequest capabilityRequest = new CapabilityRequest(
                "Test",
                "Integration"
        );

        int response = APP.client().target("http://localhost:8080/api/capabilities")
                .request()
                .post(Entity.entity(capabilityRequest, MediaType.APPLICATION_JSON_TYPE))
                .readEntity(Integer.class);

        Assertions.assertNotNull(response);
    }

    @Test
    void creatingCapabilityWhichHasNoName_shouldReturn400Error() {
        CapabilityRequest capabilityRequest = new CapabilityRequest(
                "",
                "Integration"
               );

        Response response = APP.client().target("http://localhost:8080/api/capabilities")
                .request()
                .post(Entity.entity(capabilityRequest, MediaType.APPLICATION_JSON_TYPE));

        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void creatingCapabilityWhichHasNameTooLong_shouldReturn400Error() {
        CapabilityRequest capabilityRequest = new CapabilityRequest(
                "Test".repeat(50),
                "Integration"
        );

        Response response = APP.client().target("http://localhost:8080/api/capabilities")
                .request()
                .post(Entity.entity(capabilityRequest, MediaType.APPLICATION_JSON_TYPE));

        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void creatingCapabilityWhichHasNoDescription_shouldReturn400Error() {
        CapabilityRequest capabilityRequest = new CapabilityRequest(
                "Test",
                ""
        );

        Response response = APP.client().target("http://localhost:8080/api/capabilities")
                .request()
                .post(Entity.entity(capabilityRequest, MediaType.APPLICATION_JSON_TYPE));

        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void creatingCapabilityWhichHasDescriptionTooLong_shouldReturn400Error() {
        CapabilityRequest capabilityRequest = new CapabilityRequest(
                "Test",
                "Integration".repeat(50)
        );

        Response response = APP.client().target("http://localhost:8080/api/capabilities")
                .request()
                .post(Entity.entity(capabilityRequest, MediaType.APPLICATION_JSON_TYPE));

        Assertions.assertEquals(400, response.getStatus());
    }

    void getCapabilities_shouldReturnListOfCapabilities() {
        ObjectMapper mapper = new ObjectMapper();
        Invocation.Builder response = APP.client().target("http://localhost:8080/api/capabilities")
                .request();

        List<Capabilities> capabilities = response.get(List.class);
        List<Capabilities> pojos = mapper.convertValue(capabilities, new TypeReference<List<Capabilities>>() { });

        Assertions.assertTrue(capabilities.size() > 0);
        Assertions.assertEquals("Applied Innovation", pojos.get(0).getName());
        Assertions.assertEquals(200, response.get().getStatus());

    }
}
