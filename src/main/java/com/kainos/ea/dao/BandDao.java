package com.kainos.ea.dao;

import com.kainos.ea.model.Band;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BandDao {
    public Band getBandById(int bandId, Connection c) throws SQLException {
        Statement st = c.createStatement();
        String selectStatement = "SELECT band_id, band_name FROM Bands WHERE band_id = " + bandId;

        ResultSet rs = st.executeQuery(selectStatement);

        while(rs.next()){
            Band band = new Band(
                    rs.getInt("band_id"),
                    rs.getString("band_name")
            );
            return band;
        }
        return null;
    }
}
