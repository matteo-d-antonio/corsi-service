package com.example.corsi_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class DiscenteDTO {

    private String nome;
    private String cognome;
    private Integer matricola;
    private Integer eta;
    private String cittaResidenza;

}
