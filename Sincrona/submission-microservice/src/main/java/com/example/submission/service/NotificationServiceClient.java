package com.example.submission.service;

import com.example.submission.entity.DegreeWork;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// Cliente Feign para comunicarse con el microservicio de notificaciones
@FeignClient(name = "notification-microservice", url = "http://localhost:8082/api/notifications")
public interface NotificationServiceClient {
    @PostMapping("/degreework")
    void sendDegreeWork(@RequestBody DegreeWork degreeWork);
}