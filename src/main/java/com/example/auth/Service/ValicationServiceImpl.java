package com.example.auth.Service;

import com.example.auth.Entity.Utilisateur;
import com.example.auth.Entity.Validation;
import com.example.auth.Repository.ValidationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Random;

import static java.time.temporal.ChronoUnit.MINUTES;

@Service
@AllArgsConstructor
public class ValicationServiceImpl {
    private ValidationRepository validationRepository ;
    private NotificationServiceImpl notificationService;
    public void toValidate(Utilisateur utilisateur){

        Validation validation = new Validation();
        validation.setUtilisateur(utilisateur);

        Instant created = Instant.now();
        validation.setCreation(created);
        Instant expiration = created.plus(10, MINUTES);
        validation.setExpiration(expiration);

        Random random = new Random();
        int randomInt = random.nextInt(99999);
        String code = String.format("%06d",randomInt );

        validation.setCode( code);
        this.validationRepository.save(validation);
//        this.notificationService.sendMail(validation);
    }

    public Validation readCode(String code){
        return this.validationRepository.findByCode(code).orElseThrow(()->new RuntimeException("code invide"));
    }
}
