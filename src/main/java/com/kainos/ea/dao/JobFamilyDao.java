package com.kainos.ea.dao;

import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.Capabilities;
import com.kainos.ea.model.JobFamily;
import com.kainos.ea.model.JobFamilyRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobFamilyDao {

    public List<JobFamily> getJobFamilies(Connection c) throws SQLException {
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery(
                " SELECT job_family_id, Capabilities.name, Job_Families.name "
                        + " FROM Job_Families " +
                        " INNER JOIN Capabilities on Capabilities.capability_id = Job_Families.capability_id;");

        List<JobFamily> jobFamilyList = new ArrayList<>();

        while (rs.next()) {
            JobFamily jobFamily = new JobFamily(
                    rs.getInt("job_family_id"),
                    rs.getString("Capabilities.name"),
                    rs.getString("Job_Families.name")
            );

            jobFamilyList.add(jobFamily);
        }
        return jobFamilyList;
    }

    public int createFamily(JobFamilyRequest jobFamilyRequest, Connection c) throws SQLException {
        String insertStatement = "INSERT INTO Job_Families(capability_id, name) VALUES (?,?)";

        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setInt(1, jobFamilyRequest.getCapabilityId());
        st.setString(2, jobFamilyRequest.getName());

        int affectedRows = st.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating job family failed, no rows affected.");
        }
        int idNo = 0;

        try (ResultSet rs = st.getGeneratedKeys()) {
            if (rs.next()) {
                idNo = rs.getInt(1);
            }
        }
        return idNo;
    }
}
