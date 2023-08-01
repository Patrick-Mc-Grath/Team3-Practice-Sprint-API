package com.kainos.ea.service;

import com.kainos.ea.dao.CapabilitiesDao;
import com.kainos.ea.dao.JobFamilyDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.FailedToGetJobFamilyException;
import com.kainos.ea.exception.JobFamilyNameException;
import com.kainos.ea.model.Capabilities;
import com.kainos.ea.model.JobFamily;
import com.kainos.ea.model.JobFamilyRequest;
import com.kainos.ea.util.DatabaseConnector;

import java.sql.SQLException;
import java.util.List;

public class JobFamilyService {

    public JobFamilyDao jobFamilyDao;

    public DatabaseConnector databaseConnector;

    public JobFamilyService(JobFamilyDao jobFamilyDao, DatabaseConnector databaseConnector) {
        this.jobFamilyDao= jobFamilyDao;
        this.databaseConnector = databaseConnector;
    }

    public List<JobFamily> getJobFamilies() throws FailedToGetJobFamilyException {
        try {
            return jobFamilyDao.getJobFamilies(databaseConnector.getConnection());
        } catch (SQLException | DatabaseConnectionException e) {
            throw new FailedToGetJobFamilyException();
        }
    }

    public int createJobFamily (JobFamilyRequest jobFamilyRequest) throws DatabaseConnectionException, SQLException, JobFamilyNameException {
        return jobFamilyDao.createFamily(jobFamilyRequest, databaseConnector.getConnection());
    }
}
