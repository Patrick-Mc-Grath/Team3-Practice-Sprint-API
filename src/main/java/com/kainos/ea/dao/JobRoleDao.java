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
                "SELECT * "
                        + "FROM job_roles;");

        List<JobRole> jobRoles = new ArrayList<>();

        while (rs.next()) {
            JobRole employee = new JobRole(
                    rs.getDouble("salary"),
                    rs.getInt("roleID"),
                    rs.getString("role_title")
            );

            jobRoles.add(employee);
        }
        return jobRoles;
    }
}
