package com.kainos.ea.dao;

import com.kainos.ea.exception.BandDoesNotExistException;
import com.kainos.ea.model.CompetencyResponse;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompetencyDao {

    public List<CompetencyResponse> getAllCompsAndBand(int bandId, Connection c) throws SQLException {
            Statement st = c.createStatement();

            ResultSet rs = st.executeQuery("SELECT name, description, band_name FROM Competencies JOIN Band_Competencies ON Competencies.competency_id = Band_Competencies.competency_id JOIN Bands ON Bands.band_id = Band_Competencies.band_id WHERE Band_Competencies.band_id = " + bandId);

            List<CompetencyResponse> competencyList = new ArrayList<>();

            while (rs.next()) {
                CompetencyResponse competency = new CompetencyResponse(
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("band_name")
                );
                competencyList.add(competency);
            }
            return competencyList;
    }
}


