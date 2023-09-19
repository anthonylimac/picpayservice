package com.picpayservice.service;


import com.picpayservice.dtos.NotificationDTO;
import com.picpayservice.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private RestTemplate restTemplate;
    private final String NOTIFY_URL = "https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6";

    public void sendNotification(User user, String message) throws Exception {
        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email,message);

//       ResponseEntity<String> notificationResponse = restTemplate.postForEntity(NOTIFY_URL, notificationRequest, String.class);
//       if(!(notificationResponse.getStatusCode() == HttpStatus.OK)){
//           System.out.println("Erro ao enviar notificacao");
//           throw new Exception("Notification Service failed");
//       }

        System.out.println("notification sent");
    }
}
