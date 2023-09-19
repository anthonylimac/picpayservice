package com.picpayservice.dtos;

import com.picpayservice.enums.UserType;
import com.picpayservice.model.Wallet;

import java.time.LocalDateTime;

public record UserDTO(String email, String cpf, UserType userType, String password, String firstName, String lastName, Wallet wallet, LocalDateTime createdDate) {
}
