package com.example.tutorialv2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@SequenceGenerator(name = "fornecedor_seq", sequenceName = "fornecedor_seq",
        initialValue = 1, allocationSize = 1)
@Data
@Table(name = "fornecedor")
@NoArgsConstructor
@AllArgsConstructor
public class Fornecedor {

    @Id
    @GeneratedValue(strategy =
            GenerationType.SEQUENCE, generator = "fornecedor_seq")
    private long idFornecedor;

    @NotBlank(message = "Campo nome é obrigatório.")
    private String nome;




}



