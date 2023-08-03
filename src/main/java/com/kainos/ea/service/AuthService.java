package com.kainos.ea.service;

import com.kainos.ea.dao.AuthDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.FailedToLoginException;
import com.kainos.ea.model.LoginRequest;
import com.kainos.ea.util.DatabaseConnector;
import com.kainos.ea.validator.LoginValidator;

import java.sql.SQLException;

public class AuthService
{
    public AuthDao authDao;
    public DatabaseConnector databaseConnector;
    private LoginValidator loginValidator;
    private JwtService jwtService;

    public AuthService(AuthDao authDao, DatabaseConnector databaseConnector, LoginValidator loginValidator, JwtService jwtService) {
        this.authDao = authDao;
        this.databaseConnector = databaseConnector;
        this.loginValidator = loginValidator;
        this.jwtService = jwtService;
    }
    public String Login(LoginRequest login)
            throws FailedToLoginException, DatabaseConnectionException, SQLException
    {
        String validation = loginValidator.isValidLogin(login);

        if(validation != null)
        {
            throw new FailedToLoginException(validation);
        }

        if(authDao.validLogin(login, databaseConnector.getConnection()))
        {
            return authDao.storeToken(login.getUsername(),
                    jwtService.generateJwtToken(login.getUsername()), databaseConnector.getConnection());
        }
        throw new FailedToLoginException("Login Procedure Failed");
    }
}
