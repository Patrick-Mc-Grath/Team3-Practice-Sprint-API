package com.kainos.ea.service;

import com.kainos.ea.dao.CapabilitiesDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.DescriptionLengthException;
import com.kainos.ea.exception.NameLengthException;
import com.kainos.ea.model.CapabilityRequest;
import com.kainos.ea.util.DatabaseConnector;

import java.sql.SQLException;

public class CapabilityService {

    private CapabilitiesDao capDao = new CapabilitiesDao();
    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public CapabilityService(CapabilitiesDao capDao, DatabaseConnector databaseConnector) {
        this.capDao = capDao;
        this.databaseConnector = databaseConnector;
    }

    public int insertCapability(CapabilityRequest cap) throws DatabaseConnectionException, SQLException, NameLengthException, DescriptionLengthException {
        return capDao.insertCapability(cap, databaseConnector.getConnection());
    }
    
}
