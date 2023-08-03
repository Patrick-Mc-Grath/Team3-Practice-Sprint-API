package com.kainos.ea.dao;

import com.kainos.ea.model.JobRoleResponse;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for Job Roles.
 */
public class JobRoleDao {
  /**
   * Get all job roles.
   *
   * @param c Connection to the database.
   * @return List of all job roles.
   * @throws SQLException If there is an error executing the SQL.
   */
  public List<JobRoleResponse> getRoles(Connection c) throws SQLException {
    Statement st = c.createStatement();

    ResultSet rs = st.executeQuery(
        "SELECT Job_Roles.role_id, role_title, Bands.band_id, Bands.band_name, "
          + "Job_Families.name AS `job_family_name`, Capabilities.name AS `capability_name`"
          + " FROM Job_Roles"
          + " INNER JOIN Role_Bands USING(role_id)"
          + " INNER JOIN Bands USING(band_id)"
          + " INNER JOIN Job_Families USING(job_family_id)"
          + " INNER JOIN Capabilities USING(Capability_id)"
          + " ORDER BY Job_Roles.role_id ASC;"
    );

    List<JobRoleResponse> jobRoles = new ArrayList<>();

    while (rs.next()) {
      JobRoleResponse role = new JobRoleResponse(
                rs.getInt("role_id"),
                rs.getString("role_title"),
                rs.getInt("band_id"),
                rs.getString("band_name"),
                rs.getString("job_family_name"),
                rs.getString("capability_name")
      );
      jobRoles.add(role);
    }
    return jobRoles;
  }
}
