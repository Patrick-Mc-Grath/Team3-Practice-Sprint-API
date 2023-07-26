package com.kainos.ea.service;

import com.kainos.ea.dao.JobRoleDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.FailedToCreateJobRoleException;
import com.kainos.ea.exception.InvalidJobRoleException;
import com.kainos.ea.model.JobRole;
import com.kainos.ea.model.JobRoleRequest;
import com.kainos.ea.util.DatabaseConnector;
import com.kainos.ea.validator.JobRoleValidator;

import java.sql.SQLException;
import java.util.List;

public class JobRoleService
{
    public JobRoleDao jobRoleDao;

    public DatabaseConnector databaseConnector;
    public JobRoleValidator jobRoleValidator;

    public JobRoleService(JobRoleDao jobRoleDao, DatabaseConnector databaseConnector, JobRoleValidator jobRoleValidator) {
        this.jobRoleDao = jobRoleDao;
        this.databaseConnector = databaseConnector;
        this.jobRoleValidator = jobRoleValidator;
    }

    public List<JobRole> getJobRoles() throws DatabaseConnectionException, SQLException {
        return jobRoleDao.getRoles(databaseConnector.getConnection());
    }

    public int createRole(JobRoleRequest jobRoleRequest) throws FailedToCreateJobRoleException, InvalidJobRoleException {
        try {
            String validation = jobRoleValidator.isValidJobRole(jobRoleRequest);
            if(validation != null){
                throw new InvalidJobRoleException(validation);
            }
            int id = jobRoleDao.createRole(jobRoleRequest, databaseConnector.getConnection());
            int rolebandid = jobRoleDao.createRoleBand(id, jobRoleRequest.getBandId(), databaseConnector.getConnection());
            System.out.println(rolebandid);
            if(id == -1){
                System.out.println("Error here");
                throw new FailedToCreateJobRoleException();
            }

            return id;
        } catch(SQLException | DatabaseConnectionException e) {
            System.err.println(e.getMessage());
            throw new FailedToCreateJobRoleException();
        }
    }
}
