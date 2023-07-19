package com.kainos.ea.service;
import com.kainos.ea.client.DatabaseConnectionException;
import com.kainos.ea.db.CompetencyDao;
import com.kainos.ea.cli.CompetencyRequest;
import com.kainos.ea.client.FailedToGetCompsException;
import com.kainos.ea.db.DatabaseConnector;


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

    public List<CompetencyRequest> getAllCompsWithBand() throws FailedToGetCompsException {
        List<CompetencyRequest> competencyList = null;
        try {
            Connection conn = dbConnector.getConnection();
            competencyList = compDao.getAllCompsAndBand(conn);
            return competencyList;
        } catch (SQLException | DatabaseConnectionException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetCompsException();
        }
    }



}






