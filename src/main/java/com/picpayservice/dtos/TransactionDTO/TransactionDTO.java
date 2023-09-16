package com.picpayservice.dtos.TransactionDTO;

import java.math.BigDecimal;

public  record TransactionDTO(BigDecimal value, Long senderId, Long receiverId) {
}
