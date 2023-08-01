package com.kainos.ea.dao;

import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.JobRole;
import com.kainos.ea.model.JobRoleRequest;
import com.kainos.ea.util.DatabaseConnector;
import com.kainos.ea.model.JobRoleResponse;

import java.sql.*;
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

    public int createRole(JobRoleRequest jobRoleRequest, Connection c) throws SQLException, DatabaseConnectionException {
        String insertStatement = "INSERT INTO `Job_Roles`(`role_title`, `job_family_id`) VALUES(?,?)";

        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setString(1, jobRoleRequest.getRoleTitle());
        st.setInt(2, jobRoleRequest.getJobFamilyId());

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if (rs.next()) {
            return rs.getInt(1);
        }

        return -1;
    }

    public int createRoleBand(int roleId, int bandId, Connection c) throws SQLException {
        String insertStatement = "INSERT INTO `Role_Bands`(`role_id`, `band_id`) VALUES(?,?)";

        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setInt(1, roleId);
        st.setInt(2, bandId);

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if(rs.next()) {
            System.out.println("Does this run");
            return 1;
        }

        return -1;
    }
}
