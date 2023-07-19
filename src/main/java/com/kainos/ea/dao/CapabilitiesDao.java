package com.kainos.ea.dao;

import com.kainos.ea.model.Capabilities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CapabilitiesDao {

    public List<Capabilities> getCapabilities(Connection c) throws SQLException {
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery(
                "SELECT * "
                        + "FROM Capabilities;");

        List<Capabilities> capabilitiesList = new ArrayList<>();

        while (rs.next()) {
            Capabilities capabilities = new Capabilities(
                    rs.getInt("CapabilityId"),
                    rs.getString("Name"),
                    rs.getString("Description"),
                    rs.getInt("JobRoleId")
            );

            capabilitiesList.add(capabilities);
        }
        return capabilitiesList;
    }
}
