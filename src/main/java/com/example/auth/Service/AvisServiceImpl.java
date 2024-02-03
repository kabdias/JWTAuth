package com.example.auth.Service;

import com.example.auth.Entity.Avis;
import com.example.auth.Repository.AvisReposiroty;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AvisServiceImpl {

    private AvisReposiroty avisReposiroty;

    public void addAvis(Avis avis){
        this.avisReposiroty.save(avis);
    }

}
