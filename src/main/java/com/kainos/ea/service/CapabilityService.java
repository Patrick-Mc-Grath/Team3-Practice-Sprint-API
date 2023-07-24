package com.kainos.ea.service;

import com.kainos.ea.dao.CapabilityDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.CapabilityRequest;
import com.kainos.ea.util.DatabaseConnector;
import com.kainos.ea.validator.CapabilityValidator;

import java.sql.SQLException;

public class CapabilityService {

    public CapabilityDao capDao;
    public DatabaseConnector databaseConnector;

    public int insertCapability(CapabilityRequest cap) throws DatabaseConnectionException, SQLException {
        return capDao.insertCapability(cap, databaseConnector.getConnection());
    }
    
}
