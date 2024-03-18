package com.example.tutorialv2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@SequenceGenerator(name = "perfil_seq", sequenceName = "perfil_seq",
initialValue = 1, allocationSize = 1)

@Table(name = "perfil")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Perfil {

    @Id
    @GeneratedValue(strategy =
            GenerationType.SEQUENCE, generator = "perfil_seq")
    private long idPerfil;

    @Column(name = "nome")
    @NotBlank(message = "Campo nome é obrigatório.")
    @NotNull
    private String nome;


    //1 - Admin
    //2 - Funcionario
    //3 - Cliente


}
