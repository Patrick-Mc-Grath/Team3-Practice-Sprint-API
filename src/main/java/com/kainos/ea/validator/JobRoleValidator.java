package com.kainos.ea.validator;

import com.kainos.ea.model.JobRoleRequest;

public class JobRoleValidator {
    public String isValidJobRole(JobRoleRequest jobRoleRequest) {
        if(jobRoleRequest.getRoleTitle().length() > 50) {
            return "Role Title must have a maximum length of 50 characters.";
        }
        if(jobRoleRequest.getRoleTitle().length() < 5) {
            return "Role Title too short";
        }
        if(jobRoleRequest.getJobFamilyId() == 0) {
            return "Invalid job family id";
        }
        if(jobRoleRequest.getBandId() == 0) {
            return "Invalid band id";
        }
        return null;
    }
}
