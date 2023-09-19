package com.picpayservice;

import com.picpayservice.dtos.UserDTO;
import com.picpayservice.enums.UserType;
import com.picpayservice.model.User;
import com.picpayservice.model.Wallet;
import com.picpayservice.repository.UserRepository;
import com.picpayservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class PicpayserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PicpayserviceApplication.class, args);
	}

//	@Bean
//	CommandLineRunner run(UserService userRepository){
//		return args -> {
//			Wallet wallet = new Wallet();
//			wallet.setBalance(new BigDecimal(122341));
//			UserDTO userDTO = new UserDTO("johnDoe@gmail.com", "07319919993", UserType.COMMON, "password", "John", "Doe",wallet );
//			userRepository.createUser(userDTO);
//		};
//	}



}
