package com.example.corsi_service.service;

import com.example.corsi_service.dto.DiscenteDTO;
import com.example.corsi_service.entity.Discente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class DiscenteService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    private final String discenteServiceUrl = "http://localhost:8081/discenti";  // URL del servizio discenti

    // Verifica se il discente esiste
    public Mono<DiscenteDTO> getDiscenteByNomeCognome(String nome, String cognome) {
        return webClientBuilder.build()
                .get()
                .uri(discenteServiceUrl + "?nome=" + nome + "&cognome=" + cognome) // puoi passare nome e cognome come parametri
                .retrieve()
                .bodyToMono(DiscenteDTO.class);  // Restituisce un Mono con il discente
    }

    // Crea un nuovo discente se non esiste
    public Mono<String> createDiscente(DiscenteDTO discenteDTO) {
        return webClientBuilder.build()
                .post()
                .uri(discenteServiceUrl)  // URI per creare un nuovo discente
                .bodyValue(discenteDTO)  // Passa il corpo (DiscenteDTO)
                .retrieve()
                .bodyToMono(String.class)  // Restituisce una stringa (ad esempio il messaggio di successo)
                .onErrorReturn("Errore nella creazione del discente");
    }
}
