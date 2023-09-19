package com.picpayservice.service;


import com.picpayservice.configuration.exceptions.MerchantException;
import com.picpayservice.configuration.exceptions.NotEnoughBalanceException;
import com.picpayservice.configuration.exceptions.UnableToSaveUserException;
import com.picpayservice.configuration.exceptions.UserNotFoundException;
import com.picpayservice.dtos.UserDTO;
import com.picpayservice.enums.UserType;
import com.picpayservice.model.User;
import com.picpayservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if(sender.getUserType() == UserType.MERCHANT || null == UserType.class){
            throw  new MerchantException("User is a Merchant not allowed to post transaction");
        }
        if(sender.getWallet().getBalance().compareTo(amount) < 0 ){
            throw new NotEnoughBalanceException("Not Enough money");
        }
    }

    public User findUserById(Long userId) {
         return userRepository.findUserById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public User saveUser(User user){
        userRepository.save(user);
        return user;
    }

    private Optional validateEmail(String email) {
        Optional user = userRepository.findByEmail(email);
        return user;
    }

    private Optional validateDocument(String cpf) {
        Optional user = userRepository.findUserByCpf(cpf);
        return user;
    }


    public List<User> getAllUsers(){
        return userRepository.findAll();
    }


    public String deleteUser(User user) {
        userRepository.delete(user);
        return "User Deleted";
    }

    public User createUser(UserDTO userDTO) {
        User newUser = new User(userDTO);
        validateCredentials(newUser);
        try{
            userRepository.save(newUser);
        }catch (UnableToSaveUserException exception){
            throw  new UnableToSaveUserException("Not able to save user", exception);
        }
        return newUser;
    }

    public void validateCredentials(User user) {
        Optional isDocumentValid = validateDocument(user.getCpf());
        Optional isEmailValid = validateEmail(user.getEmail());
        if (isDocumentValid.isPresent() || isEmailValid.isPresent()) {
            throw new DataIntegrityViolationException("Document in use");
        }
    }
}
