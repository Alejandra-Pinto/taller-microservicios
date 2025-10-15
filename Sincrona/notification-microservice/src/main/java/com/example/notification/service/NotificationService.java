package com.example.notification.service;

import com.example.notification.entity.*;
import com.example.notification.repository.NotificationRepository;
import com.example.notification.util.EmailSimulator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);
    private final NotificationRepository notificationRepo;
    private final EmailSimulator emailSimulator;

    public NotificationService(NotificationRepository notificationRepo, EmailSimulator emailSimulator) {
        this.notificationRepo = notificationRepo;
        this.emailSimulator = emailSimulator;
    }

    // 🔹 Método para procesar notificaciones recibidas de DegreeWork
    public void processDegreeWork(DegreeWork degreeWork) {
        logger.info("Nuevo trabajo de grado recibido: {}", degreeWork.getTitle());

        sendNotification(degreeWork.getStudent1(), "Tu trabajo de grado ha sido registrado correctamente.");
        if (degreeWork.getStudent2() != null)
            sendNotification(degreeWork.getStudent2(), "Tu trabajo de grado ha sido registrado correctamente.");

        sendNotification(degreeWork.getDirector(), "Eres director del nuevo trabajo de grado: " + degreeWork.getTitle());
        if (degreeWork.getCoDirector1() != null)
            sendNotification(degreeWork.getCoDirector1(), "Eres codirector del trabajo de grado: " + degreeWork.getTitle());
        if (degreeWork.getCoDirector2() != null)
            sendNotification(degreeWork.getCoDirector2(), "Eres segundo codirector del trabajo de grado: " + degreeWork.getTitle());

        switch (degreeWork.getEstadoP()) {
            case FORMATO_A -> sendInstitutionalNotification(
                    "coordinador@facultad.edu.co",
                    "Nuevo proyecto pendiente a evaluar",
                    "Hay un nuevo proyecto en Formato A para evaluar."
            );
            case ANTEPROYECTO -> sendInstitutionalNotification(
                    "jefe@departamento.edu.co",
                    "Nuevo anteproyecto para revisión",
                    "Hay un nuevo anteproyecto para revisión."
            );
            case MONOGRAFIA -> sendInstitutionalNotification(
                    "decanatura@facultad.edu.co",
                    "Monografía para evaluación",
                    "Se ha recibido una monografia para evaluacion final."
            );
            default -> logger.info("Estado {} sin notificación institucional especial.", degreeWork.getEstadoP());
        }

        emailSimulator.simulate("jefe@facultad.edu.co",
                "Nuevo trabajo de grado registrado: " + degreeWork.getTitle(),
                "Se ha registrado el trabajo " + degreeWork.getTitle() + " en modalidad " + degreeWork.getModality() + ".");
    }

    private void sendNotification(Object person, String message) {
        if (person instanceof Student s) {
            saveAndSend(s.getEmail(), "Notificación de estudiante", message);
        } else if (person instanceof Professor p) {
            saveAndSend(p.getEmail(), "Notificación de profesor", message);
        }
    }

    private void sendInstitutionalNotification(String email, String subject, String message) {
        saveAndSend(email, subject, message);
    }

    private void saveAndSend(String email, String subject, String message) {
        Notification n = new Notification();
        n.setRecipientEmail(email);
        n.setSubject(subject);
        n.setMessage(message);
        n.setSentAt(LocalDateTime.now());
        notificationRepo.save(n);
        emailSimulator.simulate(email, subject, message);
    }
}