package com.kainos.ea.dao;

import com.kainos.ea.model.Capabilities;
import com.kainos.ea.model.CapabilityRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CapabilitiesDao {

    public List<Capabilities> getCapabilities(Connection c) throws SQLException {
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery(
                " SELECT capability_id, name, description "
                        + " FROM Capabilities;");

        List<Capabilities> capabilitiesList = new ArrayList<>();

        while (rs.next()) {
            Capabilities capabilities = new Capabilities(
                    rs.getInt("capability_id"),
                    rs.getString("name"),
                    rs.getString("description")
            );

            capabilitiesList.add(capabilities);
        }
        return capabilitiesList;
    }
    public int insertCapability(CapabilityRequest cap, Connection c) throws SQLException {
        String insertCapabilityQuery = "INSERT INTO Capabilities (name, description) values (?, ?)";

        PreparedStatement preparedStmt = c.prepareStatement(insertCapabilityQuery, Statement.RETURN_GENERATED_KEYS);
        preparedStmt.setString(1, cap.getName());
        preparedStmt.setString(2, cap.getDescription());

        int affectedRows = preparedStmt.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating capability failed, no rows affected.");
        }
        int capNo = 0;

        try (ResultSet rs = preparedStmt.getGeneratedKeys()) {
            if (rs.next()) {
                capNo = rs.getInt(1);
            }
        }
        return capNo;
    }
}
