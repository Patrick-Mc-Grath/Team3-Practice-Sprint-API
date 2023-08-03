package com.kainos.ea.dao;

import com.kainos.ea.model.Capabilities;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * CapabilitiesDao is a class that is used to retrieve data from the database.
 */
public class CapabilitiesDao {
  /**
   * getCapabilities is a method that is used to retrieve all capabilities from the database.
   *
   * @param c of type Connection
   * @return List of Capabilities
   * @throws SQLException when there is an error with the SQL query
   */
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
}
