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


        ResultSet rs = st.executeQuery(" SELECT role_id, role_title, Capabilities.name " +
                                            " FROM Job_Roles" +
                                            " INNER JOIN Capabilities ON Capabilities.capability_id = Job_Roles.capability_id;");


        List<JobRole> jobRoles = new ArrayList<>();

        while (rs.next()) {

            JobRole employee = new JobRole(
                    rs.getInt("role_id"),
                    rs.getString("role_title"),
                    rs.getString("Capabilities.name")
            );
            jobRoles.add(employee);
        }
        return jobRoles;
    }
}
