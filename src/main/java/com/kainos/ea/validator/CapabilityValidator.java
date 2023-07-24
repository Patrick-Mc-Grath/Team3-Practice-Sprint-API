package com.kainos.ea.validator;

import com.kainos.ea.exception.DescriptionLengthException;
import com.kainos.ea.exception.NameLengthException;
import com.kainos.ea.model.CapabilityRequest;

public class CapabilityValidator {
    public boolean isValidCapability(CapabilityRequest cap) throws NameLengthException, DescriptionLengthException {
        if (cap.getName().length() < 1 || cap.getName().length() > 50) {
            throw new NameLengthException();
        }

        if (cap.getDescription().length() < 1 || cap.getDescription().length() > 100) {
            throw new DescriptionLengthException();
        }
        return true;
    }
}
