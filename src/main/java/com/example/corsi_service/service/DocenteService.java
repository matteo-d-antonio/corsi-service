package com.example.corsi_service.service;

import com.example.corsi_service.dto.DocenteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class DocenteService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    private final String docenteServiceUrl = "http://localhost:8081/docenti"; // URL del servizio Docenti-Discenti

    public Mono<DocenteDTO> getDocenteById(Long docenteId) {
        return webClientBuilder.build()
                .get()
                .uri(docenteServiceUrl + "/" + docenteId)
                .retrieve()
                .bodyToMono(DocenteDTO.class);
    }
}
