package com.example.tutorialv2.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long idUsuario;

    @Column(name = "nome")
    @NotBlank(message = "Campo nome é obrigatório.")
    private String nome;
    @Column(name = "login")
    @NotBlank(message = "Campo login é obrigatório.")
    private String login;


    @Column(name = "senha")
    @NotBlank(message = "Campo senha é obrigatório.")
    private String senha;
    @Column(name = "status")
    private int status;

    //@ManyToOne
    //@JoinColumn(name = "idPerfil")


    @Column(name = "idPerfil")
    @NotNull(message = "Campo idPerfil é obrigatório.")
    private long idPerfil;



}
