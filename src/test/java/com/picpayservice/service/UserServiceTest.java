package com.picpayservice.service;

import com.picpayservice.enums.UserType;
import com.picpayservice.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Test
    public void createUserWithEmailNotAlreadyRegistered(){
        User user = new User();
        user.setCpf("123123123123");
        user.setEmail("teste@teste.com");
        user.setFirstName("john");
        user.setLastName("Doe");
        user.setPassword("123123");
        user.setUserType(UserType.MERCHANT);


    }

}