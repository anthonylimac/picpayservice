package com.picpayservice.repository;

import com.picpayservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository  extends JpaRepository<Transaction,Long> {

}
