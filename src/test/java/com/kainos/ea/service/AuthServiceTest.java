package com.kainos.ea.service;

import com.kainos.ea.dao.AuthDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.FailedToLoginException;
import com.kainos.ea.model.LoginRequest;
import com.kainos.ea.util.DatabaseConnector;
import com.kainos.ea.validator.LoginValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest
{
    AuthDao authDao = Mockito.mock(AuthDao.class);
    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);
    LoginValidator loginValidator = Mockito.mock(LoginValidator.class);
    JwtService jwtService = Mockito.mock(JwtService.class);

    AuthService authService = new AuthService(authDao,databaseConnector,loginValidator,jwtService);

    Connection conn;

    @Test
    void Check_for_invalid_Login_exception_invalid_email()
    {
        LoginRequest testData = new LoginRequest("Test","Test");
        Mockito.when(loginValidator.isValidLogin(testData)).thenReturn("email address not valid");

        assertThrows(FailedToLoginException.class,
                () -> authService.Login(testData));
    }

    @Test
    void Check_for_invalid_Login_exception_empty_email()
    {
        LoginRequest testData = new LoginRequest("","Test");
        Mockito.when(loginValidator.isValidLogin(testData)).thenReturn("email is empty");

        assertThrows(FailedToLoginException.class,
                () -> authService.Login(testData));
    }

    @Test
    void Check_for_invalid_Login_exception_null_email()
    {
        LoginRequest testData = new LoginRequest(null,"Test");
        Mockito.when(loginValidator.isValidLogin(testData)).thenReturn("email is empty");

        assertThrows(FailedToLoginException.class,
                () -> authService.Login(testData));
    }

    @Test
    void Check_for_invalid_Login_exception_empty_password()
    {
        LoginRequest testData = new LoginRequest("mart@kainos.com","");
        Mockito.when(loginValidator.isValidLogin(testData)).thenReturn("password is empty");

        assertThrows(FailedToLoginException.class,
                () -> authService.Login(testData));
    }

    @Test
    void Check_for_invalid_Login_exception_null_password()
    {
        LoginRequest testData = new LoginRequest("mart@kainos.com",null);
        Mockito.when(loginValidator.isValidLogin(testData)).thenReturn("password is empty");

        assertThrows(FailedToLoginException.class,
                () -> authService.Login(testData));
    }

    @Test
    void Check_for_SQL_exception() throws DatabaseConnectionException, SQLException
    {
        LoginRequest testData = new LoginRequest("Test","test");

        Mockito.when(loginValidator.isValidLogin(testData)).thenReturn(null);
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jwtService.generateJwtToken(testData.getUsername())).thenReturn("token");
        Mockito.when(authDao.validLogin(testData,conn)).thenReturn(true);
        Mockito.when(authDao.storeToken(testData.getUsername(),
                jwtService.generateJwtToken(testData.getUsername()),conn)).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> authService.Login(testData));
    }

    @Test
    void Check_for_failed_to_Login_exception() throws SQLException
    {
        LoginRequest testData = new LoginRequest("Test","Test");
        Mockito.when(loginValidator.isValidLogin(new LoginRequest("Test","test"))).
                thenReturn(null);
        Mockito.when(authDao.validLogin(testData,conn)).thenReturn(false);

        assertThrows(FailedToLoginException.class,
                () -> authService.Login(testData));
    }

}
