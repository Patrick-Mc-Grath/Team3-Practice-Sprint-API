package com.kainos.ea.service;

import com.kainos.ea.dao.TrainingDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.FailedToGetTrainingException;
import com.kainos.ea.exception.TrainingDoesNotExistException;
import com.kainos.ea.model.TrainingRequest;

import java.sql.SQLException;
import java.util.List;

public class TrainingService {
    private TrainingDao trainingDao = new TrainingDao();

    public List<TrainingRequest> getTrainingByBand(int bandId) throws FailedToGetTrainingException, TrainingDoesNotExistException {
        try{
            List<TrainingRequest> trainingList = trainingDao.getTrainingByBand(bandId);

            if (trainingList == null) {
                throw new TrainingDoesNotExistException();
            }

            return trainingList;
        } catch (SQLException | DatabaseConnectionException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetTrainingException();
        }
    }
}
