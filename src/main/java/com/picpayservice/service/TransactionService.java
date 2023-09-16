package com.picpayservice.service;


import com.picpayservice.dtos.TransactionDTO.TransactionDTO;
import com.picpayservice.model.User;
import com.picpayservice.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private UserService userService;
    private TransactionRepository transactionRepository;

    public void createTransaction(TransactionDTO dto) throws Exception {{
        User sender = userService.findUserById(dto.senderId());
        User receiver = userService.findUserById(dto.receiverId());
    }

    }
}
