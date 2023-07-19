package com.kainos.ea.db;

import com.kainos.ea.cli.CompetencyRequest;
import com.kainos.ea.client.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kainos.ea.db.DatabaseConnector;

public class CompetencyDao {

    public List<CompetencyRequest> getAllCompsAndBand(Connection c) throws SQLException, DatabaseConnectionException {
            Statement st = c.createStatement();

            ResultSet rs = st.executeQuery("SELECT Name, Description, band_name FROM Competencies JOIN Band_Competencies ON Competencies.CompetencyId = Band_Competencies.CompetencyId JOIN Bands ON Bands.band_id = Band_Competencies.BandId;");

            List<CompetencyRequest> competencyList = new ArrayList<>();

            while (rs.next()) {
                CompetencyRequest competency = new CompetencyRequest(
                        rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getString("band_n" +
                                "ame")
                );
                competencyList.add(competency);
            }
            return competencyList;
    }
}


