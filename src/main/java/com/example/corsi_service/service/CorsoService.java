package com.example.corsi_service.service;

import com.example.corsi_service.dto.CorsoDTO;
import com.example.corsi_service.dto.DocenteDTO;
import com.example.corsi_service.dto.DiscenteDTO;
import com.example.corsi_service.entity.Corso;
import com.example.corsi_service.mapstruct.CorsoMapper;
import com.example.corsi_service.repository.CorsoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CorsoService {

    @Autowired
    CorsoRepository corsoRepository;
    @Autowired
    CorsoMapper corsoMapper;
    @Autowired
    private DocenteService docenteService;
    @Autowired
    private DiscenteService discenteService;
    @Autowired
    private WebClient.Builder webClientBuilder;

    private final String docenteServiceUrl = "http://localhost:8081/docenti";
    private final String discenteServiceUrl = "http://localhost:8081/discenti";


    //metodi
    private Mono<DocenteDTO> getDocenteById(Long docenteId) {
        return webClientBuilder.build()
                .get()
                .uri(docenteServiceUrl + "/" + docenteId)
                .retrieve()
                .bodyToMono(DocenteDTO.class)
                .onErrorResume(e -> Mono.empty());
    }

    public Mono<String> createCorsoWithDocenteAndDiscenti(CorsoDTO corsoDTO, Long docenteId, List<DiscenteDTO> discenti) {
        // Prima recupera il docente dal servizio Docenti
        return getDocenteById(docenteId)
                .flatMap(docente -> {
                    if (docente == null) {
                        return Mono.error(new RuntimeException("Docente non trovato!"));
                    }
                    // Se il docente esiste, imposta l'ID del docente e salva il corso
                    corsoDTO.setDocenteId(docenteId);
                    Corso corso = corsoMapper.toEntity(corsoDTO);
                    corsoRepository.save(corso);

                    return Mono.when(discenti.stream()
                                    .map(discenteDTO -> createDiscenteWithDetails(discenteDTO)) // Crea i discenti
                                    .toArray(Mono[]::new))
                            .then(Mono.just("Corso creato con successo!"));
                });
    }

    // Crea un discente se non esiste già
    public Mono<String> createDiscenteWithDetails(DiscenteDTO discenteDTO) {
        // Verifica se il discente esiste
        return discenteService.getDiscenteByNomeCognome(discenteDTO.getNome(), discenteDTO.getCognome())
                .switchIfEmpty(Mono.defer(() -> {
                    // Se il discente non esiste lo crea
                    return discenteService.createDiscente(discenteDTO) // Crea il discente
                            .thenReturn("Discente creato con successo!");
                }))
                .onErrorResume(error -> {

                    return Mono.just("Errore nella creazione del discente: " + error.getMessage());
                });
    }



    //crud
    public CorsoDTO save(CorsoDTO c){
        Corso corso = corsoMapper.toEntity(c);
        Corso savedCorso = corsoRepository.save(corso);
        return corsoMapper.toDto(savedCorso);
    }

    public CorsoDTO get(Long id) {
        Corso corso = corsoRepository.findById(id).orElseThrow();
        return corsoMapper.toDto(corso);
    }

    public List<CorsoDTO> findAll() {
        return corsoRepository.findAll().stream()
                .map(corsoMapper::toDto)
                .collect(Collectors.toList());
    }

    public CorsoDTO update(Long id, CorsoDTO corsoDTO) {
        Corso corso=corsoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Corso non trovato"));

        corso.setNome(corsoDTO.getNome());
        corso.setAnnoAccademico(corsoDTO.getAnnoAccademico());
        Corso savedCorso = corsoRepository.save(corso);
        return corsoMapper.toDto(savedCorso);
    }

    public void delete(Long id) {
        corsoRepository.deleteById(id);
    }


}
