package com.kainos.ea.validator;

import com.kainos.ea.exception.JobFamilyNameException;
import com.kainos.ea.model.JobFamilyRequest;

public class JobFamilyValidator {

    public boolean isValidJobFamily(JobFamilyRequest jobFamilyRequest) throws JobFamilyNameException {
        if(jobFamilyRequest.getName().length() > 100 || jobFamilyRequest.getName().length() < 1) {
            throw new JobFamilyNameException();
        }
        return true;
    }
}
