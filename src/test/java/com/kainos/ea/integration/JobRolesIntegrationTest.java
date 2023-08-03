package com.kainos.ea.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kainos.ea.WebServiceApplication;
import com.kainos.ea.WebServiceConfiguration;
import com.kainos.ea.dao.JobRoleDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.JobRole;
import com.kainos.ea.model.JobRoleRequest;
import com.kainos.ea.model.JobRoleResponse;
import com.kainos.ea.util.DatabaseConnector;
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
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class JobRolesIntegrationTest {
    JobRoleDao jobRoleDao = new JobRoleDao();
    DatabaseConnector databaseConnector = new DatabaseConnector();
    Connection c = databaseConnector.getConnection();

    static final DropwizardAppExtension<WebServiceConfiguration> APP = new DropwizardAppExtension<>(
            WebServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    public JobRolesIntegrationTest() throws DatabaseConnectionException, SQLException {
    }

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
  
   @Test
    void postJobRole_shouldReturnIdOfNewlyCreatedJobRole() {
        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Software Engineer",
                1,
                1
        );

        int response = APP.client().target("http://localhost:8080/api/job-roles")
                .request()
                .post(Entity.entity(jobRoleRequest, MediaType.APPLICATION_JSON_TYPE))
                .readEntity(Integer.class);

        Assertions.assertNotNull(response);
        try {
            jobRoleDao.deleteRoleBand(response, 1, c);
            jobRoleDao.deleteRole(response, c);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    @Test
    void postJobRole_shouldReturn400Error_whenRoleTitleTooLong(){
        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Software EngineerSoftware EngineerSoftware EngineerSoftware EngineerSoftware EngineerSoftware EngineerSoftware EngineerSoftware EngineerSoftware Engineer",
                1,
                1
        );

        Response response = APP.client().target("http://localhost:8080/api/job-roles")
                .request()
                .post(Entity.entity(jobRoleRequest, MediaType.APPLICATION_JSON_TYPE));

        Assertions.assertEquals(400, response.getStatus());
    }
}
