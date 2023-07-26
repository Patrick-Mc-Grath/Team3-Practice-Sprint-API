package com.kainos.ea.service;

import com.kainos.ea.dao.AuthDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.FailedToGenerateTokenException;
import com.kainos.ea.exception.FailedToLoginException;
import com.kainos.ea.exception.InvalidLoginException;
import com.kainos.ea.model.Login;
import com.kainos.ea.util.DatabaseConnector;
import com.kainos.ea.validator.LoginValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.security.auth.login.FailedLoginException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest
{
    AuthDao authDao = Mockito.mock(AuthDao.class);
    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);
    LoginValidator loginValidator = Mockito.mock(LoginValidator.class);

    AuthService authService = new AuthService(authDao,databaseConnector,loginValidator);

    Connection conn;

    @Test
    void Check_for_invalid_Login_exception_invalid_email()
    {
        Login testData = new Login("Test","Test");
        Mockito.when(loginValidator.isValidLogin(testData)).thenReturn("email address not valid");

        assertThrows(InvalidLoginException.class,
                () -> authService.Login(testData));
    }

    @Test
    void Check_for_invalid_Login_exception_empty_email()
    {
        Login testData = new Login("","Test");
        Mockito.when(loginValidator.isValidLogin(testData)).thenReturn("email is empty");

        assertThrows(InvalidLoginException.class,
                () -> authService.Login(testData));
    }

    @Test
    void Check_for_invalid_Login_exception_null_email()
    {
        Login testData = new Login(null,"Test");
        Mockito.when(loginValidator.isValidLogin(testData)).thenReturn("email is empty");

        assertThrows(InvalidLoginException.class,
                () -> authService.Login(testData));
    }

    @Test
    void Check_for_invalid_Login_exception_empty_password()
    {
        Login testData = new Login("mart@kainos.com","");
        Mockito.when(loginValidator.isValidLogin(testData)).thenReturn("password is empty");

        assertThrows(InvalidLoginException.class,
                () -> authService.Login(testData));
    }

    @Test
    void Check_for_invalid_Login_exception_null_password()
    {
        Login testData = new Login("mart@kainos.com",null);
        Mockito.when(loginValidator.isValidLogin(testData)).thenReturn("password is empty");

        assertThrows(InvalidLoginException.class,
                () -> authService.Login(testData));
    }

    @Test
    void Check_for_SQL_exception() throws DatabaseConnectionException, SQLException
    {
        Login testData = new Login("Test","test");
        Mockito.when(loginValidator.isValidLogin(testData)).thenReturn(null);
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(authDao.validLogin(testData,conn)).thenReturn(true);
        Mockito.when(authDao.generateToken(testData.getUsername(), conn)).thenThrow(SQLException.class);

        assertThrows(FailedToGenerateTokenException.class,
                () -> authService.Login(testData));
    }

    @Test
    void Check_for_failed_to_Login_exception() throws SQLException
    {
        Login testData = new Login("Test","Test");
        Mockito.when(loginValidator.isValidLogin(new Login("Test","test"))).
                thenReturn(null);
        Mockito.when(authDao.validLogin(testData,conn)).thenReturn(false);

        assertThrows(FailedToLoginException.class,
                () -> authService.Login(testData));
    }

}
