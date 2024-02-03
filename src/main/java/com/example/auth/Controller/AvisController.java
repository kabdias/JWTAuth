package com.example.auth.Controller;

import com.example.auth.Entity.Avis;
import com.example.auth.Service.AvisServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("avis")
@AllArgsConstructor
public class AvisController {

    private final AvisServiceImpl avisService;



    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void addAvis(@RequestBody Avis avis){
        this.avisService.addAvis(avis);
    }
}
