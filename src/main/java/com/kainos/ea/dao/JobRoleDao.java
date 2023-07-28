package com.kainos.ea.dao;

import com.kainos.ea.model.JobRole;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JobRoleDao
{
    public List<JobRole> getRoles(Connection c) throws SQLException {
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery(
            "SELECT Job_Roles.role_id, Job_Roles.role_title, Job_Roles.job_family_id, Capabilities.name, Role_Bands.band_id, Bands.band_name"
                  + " FROM Job_Roles"
                  + " INNER JOIN Job_Families USING(job_family_id)"
                  + " INNER JOIN Capabilities USING(capability_id)"
                  + " INNER JOIN Role_Bands USING(role_id)"
                  + " INNER JOIN Bands USING(band_id)"
                  + " ORDER BY Job_Roles.role_id ASC;"
        );
        
        List<JobRole> jobRoles = new ArrayList<>();

        while (rs.next()) {
            JobRole role = new JobRole(
                    rs.getInt("role_id"),
                    rs.getString("role_title"),
                    rs.getInt("job_family_id"),
                    rs.getString("Capabilities.name"),
                    rs.getInt("Role_Bands.band_id"),
                    rs.getString("Bands.band_name")
            );
            jobRoles.add(role);
        }

        return jobRoles;
    }
}
