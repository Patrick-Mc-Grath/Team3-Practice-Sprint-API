package com.kainos.ea.integration;

import com.kainos.ea.WebServiceApplication;
import com.kainos.ea.WebServiceConfiguration;
import com.kainos.ea.model.LoginRequest;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ExtendWith(DropwizardExtensionsSupport.class)
public class AuthIntegrationTest
{

    static final DropwizardAppExtension<WebServiceConfiguration> APP = new DropwizardAppExtension<>(
            WebServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void postWithInvalidEmail()
    {
        LoginRequest testData = new LoginRequest("Test", "test");

        Response response = APP.client().target("http://localhost:8080/api/login")
                .request()
                .post(Entity.entity(testData, MediaType.APPLICATION_JSON_TYPE));

        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void postWithValidEmailWrongPassword()
    {
        LoginRequest testData = new LoginRequest("test@kainos.com", "password");

        Response response = APP.client().target("http://localhost:8080/api/login")
                .request()
                .post(Entity.entity(testData, MediaType.APPLICATION_JSON_TYPE));

        Assertions.assertEquals(400,response.getStatus());
    }

    @Test
    void postWithValidLogin()
    {
        LoginRequest testData = new LoginRequest("mart@kainos.com", "password");

        String response = APP.client().target("http://localhost:8080/api/login")
                .request()
                .post(Entity.entity(testData, MediaType.APPLICATION_JSON_TYPE)).readEntity(String.class);

        Assertions.assertNotNull(response);
    }
}