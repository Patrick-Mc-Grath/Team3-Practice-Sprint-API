package com.kainos.ea.validator;

import com.kainos.ea.model.Login;

public class LoginValidator
{
    public String isValidLogin(Login login)
    {
        if (login.getUsername() == null)
        {
            return "username is null";
        }

        if(login.getUsername().isEmpty())
        {
            return "username is empty";
        }

        // RFC 5322 Regex for email
        if(!login.getUsername().matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"))
        {
            return "email address not valid";
        }

        if (login.getPassword() == null)
        {
            return "password is null";
        }

        if(login.getPassword().isEmpty())
        {
            return "password is empty";
        }

        return null;
    }
}
