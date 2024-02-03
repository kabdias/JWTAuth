package com.example.auth.Config;

import com.example.auth.Entity.Utilisateur;
import com.example.auth.Service.UtilisateurServiceImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@AllArgsConstructor
@Service
public class JwtService {

    private UtilisateurServiceImpl utilisateurService;

    private final String ENCRYPTION_KEY = "a55654771715bcfbf86e32b25c3fdace00fc8e8f42f1c0fcb92fdb846c573886";
    public Map<String,String> generate(String mail){
        Utilisateur utilisateur = this.utilisateurService.loadUserByUsername(mail);
        return this.generateJwt(utilisateur);
    }

    private Map<String, String> generateJwt(Utilisateur utilisateur) {

        final Map<String, String> claims = Map.of(
                "nom", utilisateur.getUsername(),
                "mail", utilisateur.getMail()
        );

        long currentDate = System.currentTimeMillis();
        long expiration = currentDate + 30 * 60 * 1000;
        final String bearer = Jwts.builder()
                .setIssuedAt(new Date(currentDate))
                .setExpiration(new Date(expiration))
                .setSubject(utilisateur.getMail())
                .setClaims(claims)
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();

        return Map.of("bearer", bearer);
    }

    private Key getKey() {
        byte[] decode = Decoders.BASE64.decode(ENCRYPTION_KEY);
        return Keys.hmacShaKeyFor(decode);
    }
}
