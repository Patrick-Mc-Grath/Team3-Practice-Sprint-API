package com.kainos.ea.service;

import com.kainos.ea.dao.AuthDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.FailedToGenerateTokenException;
import com.kainos.ea.exception.FailedToLoginException;
import com.kainos.ea.exception.InvalidLoginException;
import com.kainos.ea.model.Login;
import com.kainos.ea.util.DatabaseConnector;
import com.kainos.ea.validator.LoginValidator;

import java.sql.SQLException;

public class AuthService
{
    public AuthDao authDao;
    public DatabaseConnector databaseConnector;
    private LoginValidator loginValidator;

    public AuthService(AuthDao authDao, DatabaseConnector databaseConnector, LoginValidator loginValidator) {
        this.authDao = authDao;
        this.databaseConnector = databaseConnector;
        this.loginValidator = loginValidator;
    }
    public String Login(Login login)
            throws FailedToLoginException, FailedToGenerateTokenException, DatabaseConnectionException, SQLException,
            InvalidLoginException
    {
        String validation = loginValidator.isValidLogin(login);

        if(validation != null)
        {
            throw new InvalidLoginException(validation);
        }

        if(authDao.validLogin(login, databaseConnector.getConnection()))
        {
            try{
                return authDao.generateToken(login.getUsername(), databaseConnector.getConnection());
            } catch (SQLException e)
            {
                throw new FailedToGenerateTokenException();
            }
        }
        throw new FailedToLoginException();
    }
}
