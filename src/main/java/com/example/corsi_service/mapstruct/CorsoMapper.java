package com.example.corsi_service.mapstruct;


import com.example.corsi_service.dto.CorsoDTO;
//import com.example.demo.data.dto.DiscenteDTO;
//import com.example.demo.data.dto.DocenteDTO;
import com.example.corsi_service.entity.Corso;
//import com.example.demo.data.entity.Discente;
//import com.example.demo.data.entity.Docente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring") //, uses = {DocenteMapper.class, DiscenteMapper.class})
public interface CorsoMapper {

    CorsoDTO toDto(Corso corso);

    Corso toEntity(CorsoDTO corsoDTO);


    //@Mapping(target = "docenteDTOLight" , source = "docente")
    //@Mapping(target = "discentiDTOLight", expression = "java(corso.getDiscenti().stream().map(d -> new com.example.demo.data.dto.DiscenteDTOLight(d.getNome(), d.getCognome())).toList())")
    //CorsoDTO corsoToDto(Corso corso);

    //@Mapping(target = "docente", ignore = true)
    //@Mapping(target = "discenti", ignore = true)
    //Corso corsoToEntity(CorsoDTO corsoDTO);
}
