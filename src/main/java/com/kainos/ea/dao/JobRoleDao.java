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


        ResultSet rs = st.executeQuery(" SELECT Job_Roles.role_id, Job_Roles.role_title, Capabilities.name " +
                                        " FROM Job_Families " +
                                        " INNER JOIN Job_Roles on Job_Roles.job_family_id = Job_Families.job_family_id " +
                                        " INNER JOIN Capabilities on Capabilities.capability_id  = Job_Families.capability_id;");


        List<JobRole> jobRoles = new ArrayList<>();

        while (rs.next()) {
            JobRole role = new JobRole(
                    rs.getInt("role_id"),
                    rs.getString("role_title"),
                    rs.getString("Capabilities.name")
            );
            jobRoles.add(role);
        }

        return jobRoles;
    }
}
