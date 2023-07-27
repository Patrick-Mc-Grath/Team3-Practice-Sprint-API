package com.kainos.ea.integration;

import com.kainos.ea.WebServiceApplication;
import com.kainos.ea.WebServiceConfiguration;
import com.kainos.ea.model.CompetencyResponse;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

@ExtendWith({DropwizardExtensionsSupport.class})
public class CompetencyIntegrationTest {

    static final DropwizardAppExtension<WebServiceConfiguration> APP = new DropwizardAppExtension<>(
            WebServiceApplication.class, null,
            new ResourceConfigurationSourceProvider());

    @Test
    void getCompsByBand_shouldReturnAListOfComps() {
        int bandId = 1;
        List<CompetencyResponse> response = APP.client().target("http://localhost:8080/api/competencies/" + bandId)
                .request()
                .get(List.class);

        Assertions.assertTrue(response.size()>0);
    }


}
