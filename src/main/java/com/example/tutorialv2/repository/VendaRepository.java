package com.example.tutorialv2.repository;

import com.example.tutorialv2.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long> {
    
}
