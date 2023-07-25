package com.kainos.ea.dao;

import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.RoleBandResponse;
import com.kainos.ea.util.DatabaseConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

public class RoleBandDao {

    public List<RoleBandResponse> getRoleBands(Connection c) throws SQLException {
            Statement st = c.createStatement();

            ResultSet rs = st.executeQuery(
                    "SELECT Role_Bands.role_id, Role_Bands.band_id, role_title, band_name " +
                            "FROM Role_Bands " +
                            "INNER JOIN Job_Roles ON Role_Bands.role_id = Job_Roles.role_id " +
                            "INNER JOIN Bands ON Role_Bands.band_id = Bands.band_id;");

            List<RoleBandResponse> roleBands = new ArrayList<>();


            while (rs.next()) {
                RoleBandResponse roleBandResponse = new RoleBandResponse(
                        rs.getInt("role_id"),
                        rs.getInt("band_id"),
                        rs.getString("role_title"),
                        rs.getString("band_name")
                );
                roleBands.add(roleBandResponse);
            }
            return roleBands;
    }
}
