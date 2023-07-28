package com.kainos.ea.controller;

import com.kainos.ea.exception.BandDoesNotExistException;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.Band;
import com.kainos.ea.service.BandService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class BandControllerTest {
    BandService bandService = Mockito.mock(BandService.class);
    BandController bandController = new BandController(bandService);


    @Test
    void getBandById_shouldReturnOk_whenServiceReturnsBand() throws DatabaseConnectionException, SQLException, BandDoesNotExistException {
        int bandId = 1;
        Band expected = new Band(1, "Trainee");
        Mockito.when(bandService.getBandById(bandId)).thenReturn(expected);

        Response result = bandController.getBandById(bandId);
        assertEquals(200, result.getStatus());
    }

    @Test
    void getBandById_shouldReturn400_whenServiceThrowsBandDoesNotExistException() throws DatabaseConnectionException, SQLException, BandDoesNotExistException {
        int bandId = 1;
        Mockito.when(bandService.getBandById(bandId)).thenThrow(BandDoesNotExistException.class);

        Response result = bandController.getBandById(bandId);
        assertEquals(400, result.getStatus());
    }

    @Test
    void getBandById_shouldReturn500_whenServiceThrowsDatabaseConnectionException() throws DatabaseConnectionException, SQLException, BandDoesNotExistException {
        int bandId = 1;
        Mockito.when(bandService.getBandById(bandId)).thenThrow(DatabaseConnectionException.class);

        Response result = bandController.getBandById(bandId);
        assertEquals(500, result.getStatus());
    }

    @Test
    void getBandById_shouldReturn500_whenServiceThrowsSQLException() throws DatabaseConnectionException, SQLException, BandDoesNotExistException {
        int bandId = 1;
        Mockito.when(bandService.getBandById(bandId)).thenThrow(SQLException.class);

        Response result = bandController.getBandById(bandId);
        assertEquals(500, result.getStatus());
    }
}
