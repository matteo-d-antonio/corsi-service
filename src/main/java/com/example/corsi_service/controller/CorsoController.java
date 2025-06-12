package com.example.corsi_service.controller;

import com.example.corsi_service.dto.CorsoDTO;
import com.example.corsi_service.dto.DatiDTO;
import com.example.corsi_service.entity.Corso;
import com.example.corsi_service.service.CorsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/corsi")
public class CorsoController {
    @Autowired
    CorsoService corsoService;



    //lista
    @GetMapping("/list")
    public List<CorsoDTO> list() {
        return corsoService.findAll();

    }

    @PostMapping("/create")
    public Mono<ResponseEntity<String>> createCorso(@RequestBody DatiDTO corsoDTO) {
        return corsoService.createCorsoWithDocenteAndDiscenti(corsoDTO.getCorso(), corsoDTO.getDocenteId(), corsoDTO.getDiscenti())
                .map(message -> ResponseEntity.ok(message))  // Successo
                .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().body("Errore nella creazione del corso")));  // Gestione errore
    }

    @PutMapping("/{id}")
    public CorsoDTO update(@PathVariable Long id, @RequestBody CorsoDTO corsoDTO) {
        return corsoService.update(id, corsoDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        corsoService.delete(id);
    }


}
