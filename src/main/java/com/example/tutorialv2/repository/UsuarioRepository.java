package com.example.tutorialv2.repository;


import com.example.tutorialv2.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



import java.util.List;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByNome(String nome);

    @Query(value = "SELECT id_perfil FROM perfil p WHERE p.id_perfil = ?", nativeQuery = true)
    Long findByIdPerfil(long idPerfil);

}
