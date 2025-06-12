package com.example.corsi_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DatiDTO {
    private CorsoDTO corso;
    private Long docenteId;
    private List<DiscenteDTO> discenti;
}
