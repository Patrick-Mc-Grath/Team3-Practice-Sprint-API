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

            ResultSet rs = st.executeQuery(" SELECT Job_Roles.role_id, Job_Roles.role_title, Bands.band_name Job_Roles.job_family_id, Capabilities.name " +
                    " FROM Job_Roles " +
                    " INNER JOIN Role_Bands on Job_Roles.role_id = Role_Bands.role_id" +
                    " INNER JOIN Bands on Role_Bands.band_id = Bands.band_id " +
                    " INNER JOIN Job_Roles on Job_Roles.job_family_id = Job_Families.job_family_id " +
                    " INNER JOIN Capabilities on Capabilities.capability_id  = Job_Families.capability_id;");

            List<JobRoleResponse> jobRoles = new ArrayList<>();

            while (rs.next()) {
                JobRoleResponse role = new JobRoleResponse(
                        rs.getInt("role_id"),
                        rs.getString("role_title"),
                        rs.getString("band_name"),
                        rs.getString("job_family_name"),
                        rs.getString("Capabilities.name")
                );
                jobRoles.add(role);
            }

            return jobRoles;
        }
}
