package com.example.corsi_service.mapstruct;


import com.example.corsi_service.dto.CorsoDTO;
import com.example.corsi_service.entity.Corso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring") //, uses = {DocenteMapper.class, DiscenteMapper.class})
public interface CorsoMapper {

    CorsoDTO toDto(Corso corso);

    Corso toEntity(CorsoDTO corsoDTO);

}
