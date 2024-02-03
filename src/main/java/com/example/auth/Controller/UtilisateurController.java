package com.example.auth.Controller;

import com.example.auth.Config.JwtService;
import com.example.auth.Dto.AuthenticationDTO;
import com.example.auth.Entity.Utilisateur;
import com.example.auth.Service.UtilisateurServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("utilisateur")
@Slf4j
@AllArgsConstructor
public class UtilisateurController {

    private UtilisateurServiceImpl utilisateurService;
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    @PostMapping(path = "inscription")
    public void inscription(@RequestBody Utilisateur utilisateur){
        this.utilisateurService.addUser(utilisateur);
        log.info("inscription reussi ");
    }

    @PostMapping(path = "activation")
    public void activation(@RequestBody Map<String,String> activation){
        this.utilisateurService.activation(activation);
    }

    @PostMapping(path = "connexion")
    public Map<String,String> connexion(@RequestBody AuthenticationDTO authenticationDTO) {
        final Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationDTO.mail(), authenticationDTO.password())
        );

        if (authentication.isAuthenticated()){
           return this.jwtService.generate(authenticationDTO.mail());
        }
        return null;

    }

}
