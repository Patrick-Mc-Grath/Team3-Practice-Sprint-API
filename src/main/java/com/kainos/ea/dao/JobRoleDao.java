package com.kainos.ea.dao;

import com.kainos.ea.model.JobRole;
import com.kainos.ea.model.JobRoleResponse;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JobRoleDao
{
        public List<JobRoleResponse> getRoles(Connection c) throws SQLException {
            Statement st = c.createStatement();

            ResultSet rs = st.executeQuery(" SELECT Job_Roles.role_id, role_title, Bands.band_name, Job_Families.name AS `job_family_name`, Capabilities.name AS `capability_name`" +
                    " FROM Job_Roles" +
                    " INNER JOIN Role_Bands USING(role_id)" +
                    " INNER JOIN Bands USING(band_id)" +
                    " INNER JOIN Job_Families USING(job_family_id)" +
                    " INNER JOIN Capabilities USING(Capability_id)" +
                    " ORDER BY role_id ASC;");

            List<JobRoleResponse> jobRoles = new ArrayList<>();

            while (rs.next()) {
                JobRoleResponse role = new JobRoleResponse(
                        rs.getInt("role_id"),
                        rs.getString("role_title"),
                        rs.getString("band_name"),
                        rs.getString("job_family_name"),
                        rs.getString("capability_name")
                );
                jobRoles.add(role);
            }

            return jobRoles;
        }
}
