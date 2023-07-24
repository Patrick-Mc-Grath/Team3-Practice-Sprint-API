package com.kainos.ea.dao;

import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.CapabilityRequest;

import java.sql.*;

public class CapabilityDao {

    public int insertCapability(CapabilityRequest cap, Connection c) throws SQLException {
        String insertEmployeeQuery = "INSERT INTO Capabilities (name, description) values (?, ?)";

        PreparedStatement preparedStmt = c.prepareStatement(insertEmployeeQuery, Statement.RETURN_GENERATED_KEYS);
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
