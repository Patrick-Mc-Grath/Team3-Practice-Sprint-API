package com.kainos.ea.service;

import com.kainos.ea.dao.TrainingDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.TrainingDoesNotExistException;
import com.kainos.ea.model.TrainingRequest;
import com.kainos.ea.util.DatabaseConnector;

import java.sql.SQLException;
import java.util.List;

public class TrainingService {
    private TrainingDao trainingDao;
    private DatabaseConnector databaseConnector;


    public TrainingService(TrainingDao trainingDao, DatabaseConnector databaseConnector) {
        this.trainingDao = trainingDao;
        this.databaseConnector = databaseConnector;
    }

    public List<TrainingRequest> getTrainingByBand(int bandId) throws DatabaseConnectionException, SQLException, TrainingDoesNotExistException {
        List<TrainingRequest> trainingList = trainingDao.getTrainingByBand(bandId, databaseConnector.getConnection());

        if (trainingList.isEmpty()) {
            throw new TrainingDoesNotExistException();
        }

        return trainingList;
    }

    public List<String> getTrainingCategories() throws DatabaseConnectionException, SQLException {

        return trainingDao.getTrainingCategories(databaseConnector.getConnection());
    }
}
