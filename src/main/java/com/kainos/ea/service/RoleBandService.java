package com.kainos.ea.service;

import com.kainos.ea.dao.RoleBandDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.RoleBandResponse;
import com.kainos.ea.exception.FailedToGetRoleBandsException;
import com.kainos.ea.util.DatabaseConnector;

import java.sql.SQLException;
import java.util.List;

public class RoleBandService {
    public RoleBandDao roleBandDao;
    public DatabaseConnector databaseConnector;

    public RoleBandService(RoleBandDao roleBandDao, DatabaseConnector databaseConnector){
        this.roleBandDao = roleBandDao;
        this.databaseConnector = databaseConnector;
    }

    public List<RoleBandResponse> getRoleBands() throws DatabaseConnectionException, FailedToGetRoleBandsException {
        try{
            return roleBandDao.getRoleBands();
        } catch(SQLException e) {
            throw new FailedToGetRoleBandsException();
        }
    }
}
