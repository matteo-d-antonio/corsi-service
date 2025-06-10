package com.example.corsi_service.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
public class CorsoDTO {

    private Long id;
    private String nome;
    private Integer annoAccademico;
    private Long docenteId;

    //private DocenteDTOLight docenteDTOLight;
    //private List<DiscenteDTOLight> discentiDTOLight;

}
