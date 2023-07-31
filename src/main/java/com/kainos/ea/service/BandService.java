package com.kainos.ea.service;

import com.kainos.ea.dao.BandDao;
import com.kainos.ea.exception.BandDoesNotExistException;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.Band;
import com.kainos.ea.util.DatabaseConnector;

import java.sql.SQLException;

public class BandService {
    private BandDao bandDao;
    private DatabaseConnector databaseConnector;

    public BandService(BandDao bandDao, DatabaseConnector databaseConnector) {
        this.bandDao = bandDao;
        this.databaseConnector = databaseConnector;
    }

    public Band getBandById(int bandId) throws DatabaseConnectionException, SQLException, BandDoesNotExistException {
        Band band = bandDao.getBandById(bandId, databaseConnector.getConnection());
        if (band == null) {
            throw new BandDoesNotExistException();
        }

        return band;
    }
}
