package com.kainos.ea.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.kainos.ea.model.Band;
import com.kainos.ea.model.JobFamily;
import com.kainos.ea.model.JobRole;

public class BandDao {
    public List<Band> getAllBands(Connection c) throws SQLException {
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery(
                "SELECT band_id, band_name"
                        + " FROM Bands;");

        List<Band> bands = new ArrayList<>();

        while (rs.next()) {
            Band band = new Band(
                    rs.getInt("band_id"),
                    rs.getString("band_name")
            );

            bands.add(band);
        }
        return bands;
    }
}
