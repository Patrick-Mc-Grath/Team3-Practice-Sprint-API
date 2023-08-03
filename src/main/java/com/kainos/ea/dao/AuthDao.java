package com.kainos.ea.dao;

import com.kainos.ea.model.LoginRequest;
import java.sql.*;

public class AuthDao
{
    public boolean validLogin(LoginRequest login, Connection c) throws SQLException
    {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT Password from `User` WHERE Username = '" + login.getUsername()+"';");

            while (rs.next())
            {
                return rs.getString("password").equals(login.getPassword());
            }

        return false;
    }

    public String storeToken(String username,String jwtToken, Connection c) throws SQLException
    {

        String insertStatement = "INSERT INTO Token(Username, Token) VALUES(?,?)";

        PreparedStatement st = c.prepareStatement(insertStatement);

        st.setString(1,username);
        st.setString(2,jwtToken);

        st.executeUpdate();
        return jwtToken;
    }
}
