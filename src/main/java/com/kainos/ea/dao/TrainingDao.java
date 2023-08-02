package com.kainos.ea.dao;

import com.kainos.ea.model.TrainingResponse;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TrainingDao {

    public List<TrainingResponse> getTrainingByBand(int bandId, Connection c) throws SQLException {
        Statement st = c.createStatement();

        ArrayList<TrainingResponse> trainingList = new ArrayList<>();

        String selectStatement = "SELECT Training.name, Training.link, Training_Categories.name, Bands.band_name FROM Training JOIN Training_Bands USING(training_id) JOIN Training_Categories USING(training_category_id) JOIN Bands USING(band_id) WHERE band_id = " + bandId;

        ResultSet rs = st.executeQuery(selectStatement);

        while(rs.next()) {
            TrainingResponse training = new TrainingResponse(
                    rs.getString("Training.name"),
                    rs.getString("Training.link"),
                    rs.getString("Training_Categories.name"),
                    rs.getString("Bands.band_name")
            );
            trainingList.add(training);
        }

        return trainingList;
    }
}
