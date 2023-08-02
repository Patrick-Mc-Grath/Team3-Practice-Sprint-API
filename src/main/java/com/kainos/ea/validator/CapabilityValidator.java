package com.kainos.ea.validator;

import com.kainos.ea.exception.CapabilityDescriptionLengthException;
import com.kainos.ea.exception.CapabilityNameLengthException;
import com.kainos.ea.model.CapabilityRequest;

public class CapabilityValidator {
    public boolean isValidCapability(CapabilityRequest cap) throws CapabilityNameLengthException, CapabilityDescriptionLengthException {
        if (cap.getName().length() < 1 || cap.getName().length() > 50) {
            throw new CapabilityNameLengthException();
        }

        if (cap.getDescription().length() < 1 || cap.getDescription().length() > 100) {
            throw new CapabilityDescriptionLengthException();
        }
        return true;
    }
}
