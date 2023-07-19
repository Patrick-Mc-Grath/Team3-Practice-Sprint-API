package com.kainos.ea.dao;

import com.kainos.ea.model.TrainingRequest;
import com.kainos.ea.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TrainingDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public List<TrainingRequest> getTrainingByBand(int bandId) throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();

        ArrayList<TrainingRequest> trainingList = new ArrayList<>();

        String selectStatement = "SELECT Training.name, Training.link, Training_Categories.name FROM Training JOIN Training_Bands USING(training_id) JOIN Training_Categories USING(training_category_id) JOIN Bands USING(band_id) WHERE band_id = " + bandId;

        ResultSet rs = st.executeQuery(selectStatement);

        while(rs.next()) {
            TrainingRequest training = new TrainingRequest(
                    rs.getString("Training.name"),
                    rs.getString("Training.link"),
                    rs.getString("Training_Categories.name")
            );
            trainingList.add(training);
        }

        return trainingList;
    }
}
