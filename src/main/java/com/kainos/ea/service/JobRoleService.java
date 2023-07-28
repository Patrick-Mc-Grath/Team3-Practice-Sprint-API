package com.kainos.ea.service;

import com.kainos.ea.dao.JobRoleDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.JobRole;
import com.kainos.ea.model.JobRoleResponse;
import com.kainos.ea.util.DatabaseConnector;

import java.sql.SQLException;
import java.util.List;

public class JobRoleService
{
    public JobRoleDao jobRoleDao;

    public DatabaseConnector databaseConnector;

    public JobRoleService(JobRoleDao jobRoleDao, DatabaseConnector databaseConnector) {
        this.jobRoleDao = jobRoleDao;
        this.databaseConnector = databaseConnector;
    }

    public List<JobRoleResponse> getJobRoles() throws DatabaseConnectionException, SQLException {
        try {
            return jobRoleDao.getRoles(databaseConnector.getConnection());
        } catch(DatabaseConnectionException | SQLException e) {
            System.err.println(e);
            throw new SQLException();
        }
    }
}