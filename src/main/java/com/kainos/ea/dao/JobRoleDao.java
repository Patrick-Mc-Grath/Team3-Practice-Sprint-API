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
                "SELECT role_id, role_title, Capabilities.name, spec_link, spec_summary"
                + "FROM Job_Roles" +
                 "INNER JOIN Capabilities on Job_Roles.role_id = Capabilities.job_role_id;");

        List<JobRole> jobRoles = new ArrayList<>();

        while (rs.next()) {
            JobRole employee = new JobRole(
                    rs.getInt("role_id"),
                    rs.getString("role_title"),
                    rs.getString("name"),
                    rs.getString("spec_link"),
                    rs.getString("spec_summary")
            );

            jobRoles.add(employee);
        }
        return jobRoles;
    }
}
