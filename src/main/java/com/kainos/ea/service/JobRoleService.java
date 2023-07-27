package com.kainos.ea.service;

import com.kainos.ea.dao.JobRoleDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.JobRole;
import com.kainos.ea.util.DatabaseConnector;
import java.sql.SQLException;
import java.util.List;

/**
 * Service class for JobRole.
 */
public class JobRoleService {
  public JobRoleDao jobRoleDao;

  public DatabaseConnector databaseConnector;

  public JobRoleService(JobRoleDao jobRoleDao, DatabaseConnector databaseConnector) {
    this.jobRoleDao = jobRoleDao;
    this.databaseConnector = databaseConnector;
  }

  public List<JobRole> getJobRoles() throws DatabaseConnectionException, SQLException {
    return jobRoleDao.getRoles(databaseConnector.getConnection());
  }
}
