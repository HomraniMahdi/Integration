package tn.esprit.devminds.Service;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    void send(SimpleMailMessage mail);
}
