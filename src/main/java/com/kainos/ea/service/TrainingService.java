package com.kainos.ea.service;

import com.kainos.ea.dao.TrainingDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.FailedToGetTrainingException;
import com.kainos.ea.exception.TrainingDoesNotExistException;
import com.kainos.ea.model.TrainingResponse;
import com.kainos.ea.util.DatabaseConnector;
import java.sql.SQLException;
import java.util.List;

/**
 * Service class for Training.
 */
public class TrainingService {
  private TrainingDao trainingDao;
  private DatabaseConnector databaseConnector;


  public TrainingService(TrainingDao trainingDao, DatabaseConnector databaseConnector) {
    this.trainingDao = trainingDao;
    this.databaseConnector = databaseConnector;
  }

  /**
   * Gets all training.
   *
   * @return List of Training
   * @throws FailedToGetTrainingException if failed to get training
   */
  public List<TrainingResponse> getTrainingByBand(int bandId) throws TrainingDoesNotExistException,
      FailedToGetTrainingException {
    try {
      List<TrainingResponse> trainingList = trainingDao.getTrainingByBand(
          bandId, databaseConnector.getConnection());
      if (trainingList.isEmpty()) {
        throw new TrainingDoesNotExistException();
      }

      return trainingList;
    } catch (SQLException | DatabaseConnectionException e) {
      throw new FailedToGetTrainingException();
    }
  }
}
