package com.kainos.ea.service;
import com.kainos.ea.dao.CompetencyDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.CompetencyRequest;
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

    public List<CompetencyRequest> getAllCompsWithBand(int bandId) throws FailedToGetCompsException {
        List<CompetencyRequest> competencyList = null;
        try {
            Connection conn = dbConnector.getConnection();
            competencyList = compDao.getAllCompsAndBand(bandId, conn);
            return competencyList;
        } catch (SQLException | DatabaseConnectionException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetCompsException();
        }
    }



}






