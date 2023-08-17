package com.example.tutorialv2.data.vo.v1;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;


@Data
public class FornecedorVO implements Serializable{
    @Serial
    private static final long serialVersionUID = 1L;

    private long idFornecedor;


    private String nome;




}



