package com.picpayservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Entity(name = "wallet")
@Data
@Table(name = "wallet")
public class Wallet {

    @Id
    private long id;
    private BigDecimal amount;
    @OneToOne
    private User user;
}
