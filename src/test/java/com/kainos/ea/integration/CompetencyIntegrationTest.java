package com.kainos.ea.integration;

import com.kainos.ea.WebServiceApplication;
import com.kainos.ea.WebServiceConfiguration;
import com.kainos.ea.cli.CompetencyRequest;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CompetencyIntegrationTest {

    static final DropwizardAppExtension<WebServiceConfiguration> APP = new DropwizardAppExtension<>(
            WebServiceApplication.class, null,
            new ResourceConfigurationSourceProvider());

    @Test
    void getCompsByBand_shouldReturnAListOfComps() {
        int bandId = 1;
        List<CompetencyRequest> response = APP.client().target("http://localhost:8080/api/competencies/" + bandId)
                .request()
                .get(List.class);

        Assertions.assertTrue(response.size()>0);
    }

}
