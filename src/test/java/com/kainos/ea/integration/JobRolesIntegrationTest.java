package com.kainos.ea.integration;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kainos.ea.WebServiceApplication;
import com.kainos.ea.WebServiceConfiguration;
import com.kainos.ea.model.JobRole;
import com.kainos.ea.model.JobRoleResponse;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import javax.ws.rs.client.Invocation;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class JobRolesIntegrationTest {

    static final DropwizardAppExtension<WebServiceConfiguration> APP = new DropwizardAppExtension<>(
            WebServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void getEmployees_shouldReturnListOfEmployees()  {
        ObjectMapper mapper = new ObjectMapper();
        Invocation.Builder response = APP.client().target("http://localhost:8080/api/job-roles")
                .request();

        List<JobRoleResponse> jobRoles = response.get(List.class);
        List<JobRoleResponse> pojos = mapper.convertValue(jobRoles, new TypeReference<List<JobRoleResponse>>() { });
        
        Assertions.assertTrue(jobRoles.size() > 0);

        Assertions.assertEquals(1, pojos.get(1).getRoleId());

        Assertions.assertEquals("Software Engineer", pojos.get(1).getRoleTitle());

        Assertions.assertEquals(200, response.get().getStatus());
    }
}
