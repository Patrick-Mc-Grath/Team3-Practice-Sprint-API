package com.kainos.ea.validator;

import com.kainos.ea.model.Login;

public class LoginValidator
{
    public String isValidLogin(Login login)
    {
        // RFC 5322 Regex for email
        if(!login.getUsername().matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"))
        {
            return "email address not valid";
        }
        return null;
    }
}
