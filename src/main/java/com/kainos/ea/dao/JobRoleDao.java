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
                "SELECT role_id, role_title, job_family_id"
                        + " FROM Job_Roles;");

        List<JobRole> jobRoles = new ArrayList<>();

        while (rs.next()) {
            JobRole jobRole = new JobRole(
                    rs.getInt("role_id"),
                    rs.getString("role_title"),
                    rs.getInt("job_family_id")
            );

            jobRoles.add(jobRole);
        }
        return jobRoles;
    }
}
