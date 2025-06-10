package com.example.corsi_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "docenti-service", url = "http://localhost:8081/docenti")  // URL del servizio Docenti-Discenti
public interface DocenteClient {

    @GetMapping("/{id}")
    DocenteDTO getDocenteById(@PathVariable("id") Long id);
}
