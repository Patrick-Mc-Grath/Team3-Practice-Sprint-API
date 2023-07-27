package com.kainos.ea.service;

import com.kainos.ea.dao.BandDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.FailedToGetBandsException;
import com.kainos.ea.model.Band;
import com.kainos.ea.util.DatabaseConnector;

import java.sql.SQLException;
import java.util.List;

public class BandService {
    private BandDao bandDao;
    private DatabaseConnector databaseConnector;

    public BandService(BandDao bandDao, DatabaseConnector databaseConnector) {
        this.bandDao = bandDao;
        this.databaseConnector = databaseConnector;
    }

    public List<Band> getAllBands() throws FailedToGetBandsException {
        try{
            return bandDao.getAllBands(databaseConnector.getConnection());
        } catch (SQLException | DatabaseConnectionException e) {
            System.err.println(e);
            throw new FailedToGetBandsException();
        }
    }
}
