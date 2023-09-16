package com.picpayservice.service;


import com.picpayservice.enums.UserType;
import com.picpayservice.model.User;
import com.picpayservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if(sender.getUserType() == UserType.MERCHANT){
            throw  new Exception("User is a Merchant");
        }
        if(sender.getWallet().getAmount().compareTo(amount) < 0 ){
            throw new Exception("Not Enough money");
        }
    }

    public User findUserById(Long userId) throws Exception {
        return userRepository.findById(userId).orElseThrow(() -> new Exception("User Not Found"));
    }

    public void createUser(User user){
        userRepository.save(user);
    }
}
