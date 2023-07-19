package com.kainos.ea.service;

import com.kainos.ea.dao.CapabilitiesDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.Capabilities;
import com.kainos.ea.util.DatabaseConnector;

import java.sql.SQLException;
import java.util.List;

public class CapabilitiesService {

    public CapabilitiesDao capabilitiesDao;

    public DatabaseConnector databaseConnector;

    public CapabilitiesService(CapabilitiesDao capabilitiesDao, DatabaseConnector databaseConnector) {
        this.capabilitiesDao = capabilitiesDao;
        this.databaseConnector = databaseConnector;
    }

    public List<Capabilities> getCapabilities() throws DatabaseConnectionException, SQLException {
        return capabilitiesDao.getCapabilities(databaseConnector.getConnection());
    }

}
