package com.kainos.ea.controller;

import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.FailedToGenerateTokenException;
import com.kainos.ea.exception.FailedToLoginException;
import com.kainos.ea.exception.InvalidLoginException;
import com.kainos.ea.model.Login;
import com.kainos.ea.service.AuthService;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.sql.SQLException;

@ExtendWith(DropwizardExtensionsSupport.class)
public class AuthControllerTest
{
    private AuthService authService = Mockito.mock(AuthService.class);

    private AuthController authController = new AuthController(authService);

    private Login testData = new Login("Test","Password");

    @Test
    void check_for_SQL_Exception()
            throws DatabaseConnectionException, SQLException, FailedToLoginException, FailedToGenerateTokenException,
            InvalidLoginException
    {
        Mockito.when(authService.Login(testData)).thenThrow(new SQLException());
        Response response = authController.login(testData);
        Assertions.assertEquals(500,response.getStatus());
    }

    @Test
    void check_for_Database_Connection_Exception()
            throws DatabaseConnectionException, SQLException, FailedToLoginException, FailedToGenerateTokenException,
            InvalidLoginException
    {
        Mockito.when(authService.Login(testData)).thenThrow(new DatabaseConnectionException());
        Response response = authController.login(testData);
        Assertions.assertEquals(500,response.getStatus());
    }

    @Test
    void check_for_Failed_To_Generate_Token_Exception()
            throws DatabaseConnectionException, SQLException, FailedToLoginException, FailedToGenerateTokenException,
            InvalidLoginException
    {
        Mockito.when(authService.Login(testData)).thenThrow(new FailedToGenerateTokenException());
        Response response = authController.login(testData);
        Assertions.assertEquals(500,response.getStatus());
    }

    @Test
    void check_for_Failed_To_Login_Exception()
            throws DatabaseConnectionException, SQLException, FailedToLoginException, FailedToGenerateTokenException,
            InvalidLoginException
    {
        Mockito.when(authService.Login(testData)).thenThrow(new FailedToLoginException());
        Response response = authController.login(testData);
        Assertions.assertEquals(400,response.getStatus());
    }

    @Test
    void check_for_Invalid_Login_Exception()
            throws DatabaseConnectionException, SQLException, FailedToLoginException, FailedToGenerateTokenException,
            InvalidLoginException
    {
        Mockito.when(authService.Login(testData)).thenThrow(new InvalidLoginException("email address not valid"));
        Response response = authController.login(testData);
        Assertions.assertEquals(400,response.getStatus());
    }
}
