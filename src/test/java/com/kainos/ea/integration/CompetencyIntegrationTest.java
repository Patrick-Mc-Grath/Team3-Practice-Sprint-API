package com.kainos.ea.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kainos.ea.WebServiceApplication;
import com.kainos.ea.WebServiceConfiguration;
import com.kainos.ea.model.CompetencyResponse;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;
import java.util.List;

@ExtendWith({DropwizardExtensionsSupport.class})
public class CompetencyIntegrationTest {

    static final DropwizardAppExtension<WebServiceConfiguration> APP = new DropwizardAppExtension<>(
            WebServiceApplication.class, null,
            new ResourceConfigurationSourceProvider());

    @Test
    void getCompsByBand_shouldReturnListOfComps()  {
        int bandId = 1;
        ObjectMapper mapper = new ObjectMapper();
        Invocation.Builder response = APP.client().target("http://localhost:8080/api/competencies/" + bandId)
                .request();

        List<CompetencyResponse> competencyResponses = response.get(List.class);
        List<CompetencyResponse> pojos = mapper.convertValue(competencyResponses, new TypeReference<List<CompetencyResponse>>() { });

        Assertions.assertTrue(competencyResponses.size() > 0);

        Assertions.assertEquals("Setting Direction, Development and Accountability", pojos.get(0).getCompetencyName());

        Assertions.assertEquals(200, response.get().getStatus());
    }

    @Test
    void getCompsByBand_shouldReturn400_whenIdDoesNotExist() {
        int bandId = 123456;
        Response response = APP.client().target("http://localhost:8080/api/competencies/" + bandId).request().get(Response.class);

        Assertions.assertEquals(400, response.getStatus());
    }
}
