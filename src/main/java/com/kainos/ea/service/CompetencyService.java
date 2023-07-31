package com.kainos.ea.service;
import com.kainos.ea.dao.CompetencyDao;
import com.kainos.ea.exception.BandDoesNotExistException;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.CompetencyResponse;
import com.kainos.ea.exception.FailedToGetCompsException;
import com.kainos.ea.util.DatabaseConnector;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class CompetencyService {

    private DatabaseConnector dbConnector;
    private CompetencyDao compDao;

    public CompetencyService(CompetencyDao compDao, DatabaseConnector dbConnector) {
        this.compDao = compDao;
        this.dbConnector = dbConnector;
    }

    public List<CompetencyResponse> getAllCompsWithBand(int bandId) throws FailedToGetCompsException, BandDoesNotExistException {
        List<CompetencyResponse> competencyList = null;
        try {
            Connection conn = dbConnector.getConnection();
            competencyList = compDao.getAllCompsAndBand(bandId, conn);

            if (competencyList.isEmpty()) {
                throw new BandDoesNotExistException();
            }
            return competencyList;
        } catch (SQLException | DatabaseConnectionException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetCompsException();
        }
    }
}