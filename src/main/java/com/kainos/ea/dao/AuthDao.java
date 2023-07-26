package com.kainos.ea.dao;

import com.kainos.ea.model.Login;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.time.DateUtils;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.sql.*;
import java.time.temporal.ChronoUnit;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;
import java.util.Base64;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthDao
{
    public boolean validLogin(Login login, Connection c) throws SQLException
    {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT Password from `User` WHERE Username = '" + login.getUsername()+"';");

            while (rs.next())
            {
                return rs.getString("password").equals(login.getPassword());
            }

        return false;
    }

    public String generateToken(String username, Connection c) throws SQLException
    {
        String secret = System.getenv("SECRET");

        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
                SignatureAlgorithm.HS256.getJcaName());

        String jwtToken = Jwts.builder()
                .claim("username", username)
                .setSubject("user token: " + username)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(5l, ChronoUnit.HOURS)))
                .signWith(hmacKey)
                .compact();

        Date expiry = DateUtils.addHours(new Date(),5);

        String insertStatement = "INSERT INTO Token(Username, Token, Expiry) VALUES(?,?,?)";

        PreparedStatement st = c.prepareStatement(insertStatement);

        st.setString(1,username);
        st.setString(2,jwtToken);
        st.setTimestamp(3, new java.sql.Timestamp(expiry.getTime()));

        st.executeUpdate();
        return jwtToken;
    }
}
