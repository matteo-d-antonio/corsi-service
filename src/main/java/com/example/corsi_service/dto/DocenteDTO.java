package com.example.corsi_service.dto;  // Assicurati che il pacchetto sia corretto

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
//import lombok.Getter;
//import lombok.Setter;

//@Getter
//@Setter

@NoArgsConstructor          //genera costruttore senza argomenti
@AllArgsConstructor         //genera costruttore  con tutti i campi degli argomenti
@Data                       //genera getters and setters ha bisogno di un argument constructor
public class DocenteDTO {

    private long id;
    private String nome;
    private String cognome;
    private Date dataNascita;

    public DocenteDTO(Long id, String nome, String cognome) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
    }

}
