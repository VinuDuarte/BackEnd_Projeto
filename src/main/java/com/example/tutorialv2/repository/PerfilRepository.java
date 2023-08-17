package com.example.tutorialv2.repository;

import com.example.tutorialv2.data.vo.v1.PerfilVO;
import com.example.tutorialv2.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    //LIST USERS CLIENTES
    List<Perfil> findByNome(String nome);



}
