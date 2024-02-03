package com.example.auth.Service;

import com.example.auth.Entity.Role;
import com.example.auth.Entity.Utilisateur;
import com.example.auth.Entity.Validation;
import com.example.auth.Repository.UtilisateurRepository;
import com.example.auth.Utils.TypeRole;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UtilisateurServiceImpl implements UserDetailsService {

    private UtilisateurRepository utilisateurRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private ValicationServiceImpl valicationService;
    public void addUser(Utilisateur utilisateur){
        String pwdEncode = this.bCryptPasswordEncoder.encode(utilisateur.getPassword());
        utilisateur.setPassword(pwdEncode);

        Optional<Utilisateur> utilisateurMail = this.utilisateurRepository.findByMail(utilisateur.getMail());
        if (utilisateurMail.isPresent()){
            throw new RuntimeException("l'utilisateur existe deja");
        } else if (!utilisateur.getMail().contains("@") || !utilisateur.getMail().contains(".")) {
            throw new RuntimeException("email invalide");
        }

        Role roleUtilisateur = new Role();
        roleUtilisateur.setTypeRole(TypeRole.UTILISATEUR);
        utilisateur.setRole(roleUtilisateur);

        utilisateur = this.utilisateurRepository.save(utilisateur);
        this.valicationService.toValidate(utilisateur);
    }


    public void activation(Map<String , String > activation) {
        Validation validation = this.valicationService.readCode(activation.get("code"));
        if (Instant.now().isAfter(validation.getExpiration())){
            throw new RuntimeException("code expiré");
        }
        Utilisateur utilisateurActive = this.utilisateurRepository.findById(validation.getUtilisateur().getId())
                                        .orElseThrow(()-> new RuntimeException("utilisateur inconnu"));
        utilisateurActive.setActif(true);
        this.utilisateurRepository.save(utilisateurActive);

    }

    @Override
    public Utilisateur loadUserByUsername(String mail) throws UsernameNotFoundException {
        return this.utilisateurRepository.findByMail(mail).orElseThrow(()-> new RuntimeException("aucun utilisateur ne correspond"));
    }
}
