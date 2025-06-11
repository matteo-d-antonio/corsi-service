package com.example.corsi_service.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
public class CorsoDTO {

    private String nome;
    private Integer annoAccademico;
    private Long docenteId;

    public Long getDocenteId() {
        return docenteId;
    }

    // Setter per docenteId (se non usi Lombok per generarlo)
    public void setDocenteId(Long docenteId) {
        this.docenteId = docenteId;
    }

}
