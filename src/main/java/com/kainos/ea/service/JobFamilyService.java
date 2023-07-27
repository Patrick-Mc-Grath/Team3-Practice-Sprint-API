package com.kainos.ea.service;

import com.kainos.ea.dao.JobFamilyDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.FailedToGetJobFamiliesException;
import com.kainos.ea.model.JobFamily;
import com.kainos.ea.util.DatabaseConnector;

import java.sql.SQLException;
import java.util.List;

public class JobFamilyService {
    public JobFamilyDao jobFamilyDao;
    public DatabaseConnector databaseConnector;

    public JobFamilyService(JobFamilyDao jobFamilyDao, DatabaseConnector databaseConnector) {
        this.jobFamilyDao = jobFamilyDao;
        this.databaseConnector = databaseConnector;
    }

    public List<JobFamily> getJobFamilies() throws FailedToGetJobFamiliesException {
        try{
            return jobFamilyDao.getJobFamilies(databaseConnector.getConnection());
        } catch (SQLException | DatabaseConnectionException e) {
            System.err.println(e);
            throw new FailedToGetJobFamiliesException();
        }
    }
}
