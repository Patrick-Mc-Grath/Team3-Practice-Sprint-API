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
                " SELECT job_family_id, Capabilities.name, Capabilities.capability_id, Job_Families.name "
                        + " FROM Job_Families " +
                        " INNER JOIN Capabilities on Capabilities.capability_id = Job_Families.capability_id;");

        List<JobFamily> jobFamilyList = new ArrayList<>();

        while (rs.next()) {
            JobFamily jobFamily = new JobFamily(
                    rs.getInt("job_family_id"),
                    rs.getString("Capabilities.name"),
                    rs.getInt("Capabilities.capability_id"),
                    rs.getString("Job_Families.name")
            );

            jobFamilyList.add(jobFamily);
        }

        return jobFamilyList;

        }
    }

