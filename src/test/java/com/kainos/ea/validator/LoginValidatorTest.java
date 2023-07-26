package com.kainos.ea.validator;

import com.kainos.ea.model.Login;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class LoginValidatorTest
{
    private LoginValidator loginValidator = new LoginValidator();
    @Test
    void check_Invalid_email()
    {
        String result = loginValidator.isValidLogin(new Login("Test","Test"));
        assertEquals("email address not valid",result);
    }

    @Test
    void check_null_email()
    {
        String result = loginValidator.isValidLogin(new Login(null,"Test"));
        assertEquals("email is empty",result);
    }

    @Test
    void check_empty_email()
    {
        String result = loginValidator.isValidLogin(new Login("","Test"));
        assertEquals("email is empty",result);
    }

    @Test
    void check_null_password()
    {
        String result = loginValidator.isValidLogin(new Login("mart@kainos.com",null));
        assertEquals("password is empty",result);
    }

    @Test
    void check_empty_password()
    {
        String result = loginValidator.isValidLogin(new Login("mart@kainos.com",""));
        assertEquals("password is empty",result);
    }

    @Test
    void check_valid_email()
    {
        String result = loginValidator.isValidLogin(new Login("Test@gmail.com","Test"));
        assertEquals(null,result);
    }
}
