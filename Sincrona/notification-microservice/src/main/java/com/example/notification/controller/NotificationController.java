package com.example.notification.controller;

import com.example.notification.entity.DegreeWork;
import com.example.notification.entity.Notification;
import com.example.notification.repository.NotificationRepository;
import com.example.notification.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationRepository repo;
    private final NotificationService notificationService;

    public NotificationController(NotificationRepository repo, NotificationService notificationService) {
        this.repo = repo;
        this.notificationService = notificationService;
    }

    // Obtener todas las notificaciones guardadas
    @GetMapping
    public List<Notification> getAll() {
        return repo.findAll();
    }

    // Endpoint que recibe el DegreeWork desde Submission (llamada sincrónica)
    @PostMapping("/degreework")
    public ResponseEntity<String> receiveDegreeWork(@RequestBody DegreeWork degreeWork) {
        notificationService.processDegreeWork(degreeWork);
        return ResponseEntity.ok("Notificaciones procesadas para el trabajo: " + degreeWork.getTitle());
    }
}