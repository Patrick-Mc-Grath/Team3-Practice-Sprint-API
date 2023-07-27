package com.kainos.ea.service;

import com.kainos.ea.dao.CapabilityDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.DescriptionLengthException;
import com.kainos.ea.exception.NameLengthException;
import com.kainos.ea.model.CapabilityRequest;
import com.kainos.ea.util.DatabaseConnector;

import java.sql.SQLException;

public class CapabilityService {

    private CapabilityDao capDao = new CapabilityDao();
    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public CapabilityService(CapabilityDao capDao, DatabaseConnector databaseConnector) {
        this.capDao = capDao;
        this.databaseConnector = databaseConnector;
    }

    public int insertCapability(CapabilityRequest cap) throws DatabaseConnectionException, SQLException, NameLengthException, DescriptionLengthException {
        return capDao.insertCapability(cap, databaseConnector.getConnection());
    }
    
}
