package com.kainos.ea.controller;

import com.kainos.ea.dao.AuthDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.FailedToLoginException;
import com.kainos.ea.model.LoginRequest;
import com.kainos.ea.service.AuthService;
import com.kainos.ea.service.JwtService;
import com.kainos.ea.util.DatabaseConnector;
import com.kainos.ea.validator.LoginValidator;
import io.swagger.annotations.Api;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Backend API Authorization")
@Path("/api")
public class AuthController
{
    private AuthService authService;

    public AuthController()
    {
        authService = new AuthService(new AuthDao(), new DatabaseConnector(), new LoginValidator(), new JwtService());
    }

    public AuthController(AuthService authService)
    {
        this.authService = authService;
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest login)
    {
        try
        {
            return Response.ok(authService.Login(login)).build();
        } catch (FailedToLoginException e)
        {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (SQLException | DatabaseConnectionException e)
        {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
