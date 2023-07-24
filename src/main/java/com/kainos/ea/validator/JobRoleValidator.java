package com.kainos.ea.validator;

import com.kainos.ea.model.JobRoleRequest;

public class JobRoleValidator {
    public String isValidJobRole(JobRoleRequest jobRoleRequest) {
        if(jobRoleRequest.getRoleTitle().length() > 50) {
            return "Role Title must have a maximum length of 50 characters.";
        }
        return null;
    }
}
