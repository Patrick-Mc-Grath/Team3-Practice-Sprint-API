package com.kainos.ea.dao;

import com.kainos.ea.model.TrainingRequest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TrainingDao {

    public List<TrainingRequest> getTrainingByBand(int bandId, Connection c) throws SQLException {
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

    public List<String> getTrainingCategories(Connection c) throws SQLException {
        Statement st = c.createStatement();

        ArrayList<String> categories = new ArrayList<>();

        String selectStatement = "SELECT name from Training_Categories";

        ResultSet rs = st.executeQuery(selectStatement);

        while (rs.next()){
            categories.add(rs.getString("name"));
        }

        return categories;
    }
}
