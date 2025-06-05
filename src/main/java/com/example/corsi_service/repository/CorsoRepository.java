package com.example.corsi_service.repository;


import com.example.corsi_service.entity.Corso;
//import com.example.demo.data.entity.Discente;
//import com.example.demo.data.entity.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CorsoRepository extends JpaRepository<Corso, Long>{

    //@Query("SELECT c FROM Corso c WHERE c.docente = :docente")
    //List<Corso> findByDocente(@Param("docente") Docente docente);

    //@Query("SELECT c FROM Corso c WHERE :discente MEMBER OF c.discenti")
    //List<Corso> findByDiscente(@Param("discente") Discente discente);

}
