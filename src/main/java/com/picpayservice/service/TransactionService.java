package com.picpayservice.service;


import com.picpayservice.dtos.TransactionDTO;
import com.picpayservice.model.Transaction;
import com.picpayservice.model.User;
import com.picpayservice.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TransactionService {

    @Autowired
    private UserService userService;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private NotificationService notificationService;

    public Transaction createTransaction(TransactionDTO transactionDTO) throws Exception {
        User sender = userService.findUserById(transactionDTO.senderId());
        User receiver = userService.findUserById(transactionDTO.receiverId());
        userService.validateTransaction(sender, transactionDTO.value());
//        boolean isAuthorized = this.authorizeTransaction(sender, transactionDTO.value());
//        if(!isAuthorized){
//            throw  new Exception("Transaction not authorized");
//        }
        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDTO.value());
        transaction.setReceiver(receiver);
        transaction.setSender(sender);
        transaction.setTimeStamp(LocalDateTime.now());
        sender.getWallet().setBalance(sender.getWallet().getBalance().subtract(transaction.getAmount()));
        receiver.getWallet().setBalance(receiver.getWallet().getBalance().add(transaction.getAmount()));

        transactionRepository.save(transaction);
        userService.saveUser(sender);
        userService.saveUser(receiver);
        notificationService.sendNotification(sender, "Transaction successful");
        notificationService.sendNotification(receiver, "Transaction successful");
        return  transaction;

    }

//    private Transaction mountTransaction(TransactionDTO transactionDTO){
//
//    }

    public boolean authorizeTransaction(User sender, BigDecimal amount){
        ResponseEntity<Map> authorizationTemplate = restTemplate.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", Map.class);

        if(authorizationTemplate.getStatusCode() == HttpStatus.OK ){
            String message = (String) authorizationTemplate.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);
        }else return false;

    }

    public List<Transaction> getAllTransactions(){
        return  transactionRepository.findAll();
    }




}
