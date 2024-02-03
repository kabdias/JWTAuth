package com.example.auth.Service;

import com.example.auth.Entity.Validation;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationServiceImpl {

//   JavaMailSender javaMailSender;
//
//
//    public void sendMail(Validation validation) {
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setFrom("no-reply@local.tech");
//        mailMessage.setTo(validation.getUtilisateur().getMail());
//        mailMessage.setSubject("votre mail d'activation");
//
//        String text = String.format(
//                "Bonjour %s, <br/> Votre code d'activation est %s <br/>",
//                validation.getUtilisateur().getUsername(),
//                validation.getCode()
//        );
//
//        mailMessage.setText(text);
//        javaMailSender.send(mailMessage);
//    }
}
