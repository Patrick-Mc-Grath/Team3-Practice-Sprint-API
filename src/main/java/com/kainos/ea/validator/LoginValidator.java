package com.kainos.ea.validator;

import com.kainos.ea.model.Login;

public class LoginValidator
{
    public String isValidLogin(Login login)
    {
        if (login.getUsername() == null || login.getUsername().isEmpty())
        {
            return "email is empty";
        }

        // RFC 5322 Regex for email
        if(!login.getUsername().matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"))
        {
            return "email address not valid";
        }

        if (login.getPassword() == null || login.getPassword().isEmpty())
        {
            return "password is empty";
        }

        return null;
    }
}
