package com.picpayservice.model;


import com.picpayservice.dtos.UserDTO;
import com.picpayservice.enums.UserType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity(name = "users")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String cpf;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    private String password;
    private String firstName;
    private String lastName;

    @Transient
    private LocalDateTime createdDate;

    @OneToOne(cascade = CascadeType.ALL)
    private Wallet wallet;

    public User(UserDTO userDTO){
        this.email = userDTO.email();
        this.cpf = userDTO.cpf();
        this.userType = userDTO.userType();
        this.password = userDTO.password();
        this.firstName = userDTO.firstName();
        this.lastName = userDTO.lastName();
//     this.balance = userDTO.balance();
         this.wallet = userDTO.wallet();
         this.createdDate = LocalDateTime.now();
    }

    public User(Long id, String email, String cpf, UserType userType, String password, String firstName, String lastName, Wallet wallet) {
        this.id = id;
        this.email = email;
        this.cpf = cpf;
        this.userType = userType;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.wallet = wallet;
        this.createdDate = LocalDateTime.now();
    }

    public User() {
    }
}
