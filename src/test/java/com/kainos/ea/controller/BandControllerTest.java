package com.kainos.ea.controller;

import com.kainos.ea.exception.FailedToGetBandsException;
import com.kainos.ea.model.Band;
import com.kainos.ea.service.BandService;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(DropwizardExtensionsSupport.class)
public class BandControllerTest {
    BandService bandService = Mockito.mock(BandService.class);
    BandController bandController = new BandController(bandService);

    @Test
    void getAllBands_shouldReturnOk_whenServiceReturnsBandList() throws FailedToGetBandsException {
        List<Band> bandList = new ArrayList<>();
        Mockito.when(bandService.getAllBands()).thenReturn(bandList);

        Response result = bandController.getAllBands();
        assertEquals(200, result.getStatus());
    }

    @Test
    void getAllBands_shouldReturnServerError_whenServiceThrowsFailedToGetBandsException() throws FailedToGetBandsException {
        Mockito.when(bandService.getAllBands()).thenThrow(FailedToGetBandsException.class);

        Response result = bandController.getAllBands();
        assertEquals(500, result.getStatus());
    }
}
