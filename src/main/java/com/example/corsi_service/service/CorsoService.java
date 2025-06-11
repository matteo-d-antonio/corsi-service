package com.example.corsi_service.service;

import com.example.corsi_service.dto.CorsoDTO;
import com.example.corsi_service.dto.DocenteDTO;
import com.example.corsi_service.entity.Corso;
import com.example.corsi_service.mapstruct.CorsoMapper;
import com.example.corsi_service.repository.CorsoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections; // Import per Collections.emptyList()
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream; // Import per Stream

@Service
public class CorsoService {

    @Autowired
    private CorsoRepository corsoRepository;
    @Autowired
    private CorsoMapper corsoMapper;
    @Autowired
    private DocenteService docenteService;


    //metodi per crud
    public List<CorsoDTO> findAll() {
        return corsoRepository.findAll().stream()
                .map(corsoMapper::toDto)
                .collect(Collectors.toList());
    }


    public CorsoDTO get(Long id) {
        Corso corso =corsoRepository.findById(id).orElseThrow();
        return corsoMapper.toDto(corso);
    }


    public CorsoDTO save(CorsoDTO corsoDTO){

        Mono<DocenteDTO> docente = docenteService.getDocenteById(corsoDTO.getDocenteId());

        if (docente == null || docente.block() == null) {
            throw new EntityNotFoundException("Docente non trovato!");
        }

        Corso corso = corsoMapper.toEntity(corsoDTO);
        Corso savedCorso = corsoRepository.save(corso);
        return corsoMapper.toDto(savedCorso);
    }

    public CorsoDTO update(Long id, CorsoDTO corsoDTO) {
        Corso corso=corsoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Corso non trovato"));

        if(corsoDTO.getNome()!=null) corso.setNome(corsoDTO.getNome());
        if (corsoDTO.getAnnoAccademico()!=null) corso.setAnnoAccademico(corsoDTO.getAnnoAccademico());

        Corso savedCorso = corsoRepository.save(corso);
        return corsoMapper.toDto(savedCorso);
    }


    public void delete(Long id) {
        corsoRepository.deleteById(id);
    }


}
