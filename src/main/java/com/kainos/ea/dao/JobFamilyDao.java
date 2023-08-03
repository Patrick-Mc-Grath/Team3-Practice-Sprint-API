package com.kainos.ea.dao;

import com.kainos.ea.model.JobFamily;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JobFamilyDao {
    public List<JobFamily> getJobFamilies(Connection c) throws SQLException {
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery(
                "SELECT job_family_id, capability_id, name"
                        + " FROM Job_Families;");

        List<JobFamily> jobFamilies = new ArrayList<>();

        while (rs.next()) {
            JobFamily jobFamily = new JobFamily(
                    rs.getInt("job_family_id"),
                    rs.getInt("capability_id"),
                    rs.getString("name")
            );

            jobFamilies.add(jobFamily);
        }
        return jobFamilies;
    }
}
