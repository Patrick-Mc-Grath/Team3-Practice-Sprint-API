package com.kainos.ea.validator;

import com.kainos.ea.exception.DescriptionLengthException;
import com.kainos.ea.exception.NameLengthException;
import com.kainos.ea.model.CapabilityRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CapabilityValidatorTest {

    CapabilityValidator capValidator = new CapabilityValidator();

    @Test
    public void isValidCapability_shouldReturnTrue_whenValidCapability() throws NameLengthException, DescriptionLengthException {
        CapabilityRequest capRequest = new CapabilityRequest(
                "Test",
                "Testing"
        );

        assertTrue(capValidator.isValidCapability(capRequest));
    }

    @Test
    public void isValidCapability_shouldThrowNameLengthException_whenNameIsTooLong() throws NameLengthException, DescriptionLengthException {
        CapabilityRequest capRequest = new CapabilityRequest(
                "Test".repeat(50),
                "Testing"
        );

        assertThrows(NameLengthException.class,
                () -> capValidator.isValidCapability(capRequest));
    }

    @Test
    public void isValidCapability_shouldThrowNameLengthException_whenNameIsEmpty() throws NameLengthException, DescriptionLengthException {
        CapabilityRequest capRequest = new CapabilityRequest(
                "",
                "Testing"
        );

        assertThrows(NameLengthException.class,
                () -> capValidator.isValidCapability(capRequest));
    }

    @Test
    public void isValidCapability_shouldThrowDescriptionLengthException_whenDescriptionIsTooLong() throws NameLengthException, DescriptionLengthException {
        CapabilityRequest capRequest = new CapabilityRequest(
                "Test",
                "Testing".repeat(50)
        );

        assertThrows(DescriptionLengthException.class,
                () -> capValidator.isValidCapability(capRequest));
    }

    @Test
    public void isValidCapability_shouldThrowDescriptionLengthException_whenDescriptionIsEmpty() throws NameLengthException, DescriptionLengthException {
        CapabilityRequest capRequest = new CapabilityRequest(
                "Test",
                ""
        );

        assertThrows(DescriptionLengthException.class,
                () -> capValidator.isValidCapability(capRequest));
    }
}
