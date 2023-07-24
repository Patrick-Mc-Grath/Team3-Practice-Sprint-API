package com.kainos.ea.dao;

import com.kainos.ea.model.JobRole;
import com.kainos.ea.model.JobRoleRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobRoleDao
{
    public List<JobRole> getRoles(Connection c) throws SQLException {
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery(
                "SELECT role_id, role_title, spec_link"
                        + " FROM Job_Roles;");

        List<JobRole> jobRoles = new ArrayList<>();

        while (rs.next()) {
            JobRole jobRole = new JobRole(
                    rs.getInt("role_id"),
                    rs.getString("role_title"),
                    rs.getString("spec_link")
            );

            jobRoles.add(jobRole);
        }
        return jobRoles;
    }

    public int createRole(JobRoleRequest jobRoleRequest, Connection c) throws SQLException {
        String insertStatement = "INSERT INTO `Job_Roles`(`role_title`, `job_family_id`) VALUES(?,?)";

        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setString(1, jobRoleRequest.getRoleTitle());
        st.setInt(2, jobRoleRequest.getJobFamilyId());

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if (rs.next()) {
            if(createRoleBand(rs.getInt(1), jobRoleRequest.getBandId(), c) == 1) {
                return rs.getInt(1);
            }
        }

        return -1;
    }

    private int createRoleBand(int roleId, int bandId, Connection c) throws SQLException {
        String insertStatement = "INSERT INTO `Role_Bands`(`role_id`, `band_id`) VALUES(?,?)";

        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setInt(1, roleId);
        st.setInt(2, bandId);

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if(rs.next()) {
            return 1;
        }

        return -1;
    }
}
