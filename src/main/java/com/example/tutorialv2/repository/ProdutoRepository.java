package com.example.tutorialv2.repository;

import com.example.tutorialv2.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository  extends JpaRepository<Produto , Long> {

    List<Produto> findByNome(String nome);

    @Query(value = "SELECT id_fornecedor FROM fornecedor f  WHERE f.id_fornecedor = ?", nativeQuery = true)
    Long findByIdFornecedor(long IdFornecedor);

    @Query(value = "SELECT * FROM produto p WHERE p.nome  ILIKE LOWER(CONCAT('%', :buscar, '%'))",nativeQuery = true)
    List<Produto> searchByNomeIgnoreCase(String buscar);



}
