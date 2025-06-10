package com.example.corsi_service.controller;


//import org.springframework.ui.Model;
//import com.example.demo.service.DiscenteService;
//import com.example.demo.service.DocenteService;
import com.example.corsi_service.dto.CorsoDTO;
import com.example.corsi_service.entity.Corso;
import com.example.corsi_service.service.CorsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/corsi")
public class CorsoController {
    @Autowired
    CorsoService corsoService;

    @Autowired
    RestTemplate restTemplate;

    private static final String DOCENTE_SERVICE_URL = "http://localhost:8081/docenti/";


    //lista
    @GetMapping //("/list")
    public List<CorsoDTO> list() {
        return corsoService.findAll();

    }

    /*@PostMapping
    public CorsoDTO create(@RequestBody CorsoDTO corsoDTO) {
        return corsoService.save(corsoDTO);
    }*/
    @PostMapping("/create")
    public ResponseEntity<String> createCorso(@RequestBody CorsoDTO corsoDTO, @RequestParam Long docenteId) {
        // Chiamata RestTemplate per verificare se il docente esiste
        String docenteUrl = DOCENTE_SERVICE_URL + docenteId;
        try {
            // Chiamata GET per verificare se il docente esiste
            ResponseEntity<String> docenteResponse = restTemplate.getForEntity(docenteUrl, String.class);

            if (docenteResponse.getStatusCode() == HttpStatus.OK) {
                // Se il docente esiste, salviamo il corso
                corsoDTO.setDocenteId(docenteId); // Imposta l'ID del docente nel CorsoDTO
                corsoService.save(corsoDTO);
                return new ResponseEntity<>("Corso creato con successo!", HttpStatus.CREATED);
            } else {
                // Se il docente non esiste, restituiamo un errore
                return new ResponseEntity<>("Docente non trovato!", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Se c'è un errore nella chiamata RestTemplate (es. il servizio docente è giù)
            return new ResponseEntity<>("Errore nella comunicazione con il servizio docente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
