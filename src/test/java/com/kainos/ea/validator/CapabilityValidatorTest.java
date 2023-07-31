package com.kainos.ea.validator;

import com.kainos.ea.exception.CapabilityDescriptionLengthException;
import com.kainos.ea.exception.CapabilityNameLengthException;
import com.kainos.ea.model.CapabilityRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CapabilityValidatorTest {

    CapabilityValidator capValidator = new CapabilityValidator();

    @Test
    public void isValidCapability_shouldReturnTrue_whenValidCapability() throws CapabilityNameLengthException, CapabilityDescriptionLengthException {
        CapabilityRequest capRequest = new CapabilityRequest(
                "Test",
                "Testing"
        );

        assertTrue(capValidator.isValidCapability(capRequest));
    }

    @Test
    public void isValidCapability_shouldThrowNameLengthException_whenNameIsTooLong() throws CapabilityNameLengthException, CapabilityDescriptionLengthException {
        CapabilityRequest capRequest = new CapabilityRequest(
                "Test".repeat(50),
                "Testing"
        );

        assertThrows(CapabilityNameLengthException.class,
                () -> capValidator.isValidCapability(capRequest));
    }

    @Test
    public void isValidCapability_shouldThrowNameLengthException_whenNameIsEmpty() throws CapabilityNameLengthException, CapabilityDescriptionLengthException {
        CapabilityRequest capRequest = new CapabilityRequest(
                "",
                "Testing"
        );

        assertThrows(CapabilityNameLengthException.class,
                () -> capValidator.isValidCapability(capRequest));
    }

    @Test
    public void isValidCapability_shouldThrowDescriptionLengthException_whenDescriptionIsTooLong() throws CapabilityNameLengthException, CapabilityDescriptionLengthException {
        CapabilityRequest capRequest = new CapabilityRequest(
                "Test",
                "Testing".repeat(50)
        );

        assertThrows(CapabilityDescriptionLengthException.class,
                () -> capValidator.isValidCapability(capRequest));
    }

    @Test
    public void isValidCapability_shouldThrowDescriptionLengthException_whenDescriptionIsEmpty() throws CapabilityNameLengthException, CapabilityDescriptionLengthException {
        CapabilityRequest capRequest = new CapabilityRequest(
                "Test",
                ""
        );

        assertThrows(CapabilityDescriptionLengthException.class,
                () -> capValidator.isValidCapability(capRequest));
    }
}
