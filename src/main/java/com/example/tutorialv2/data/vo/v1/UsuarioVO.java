package com.example.tutorialv2.data.vo.v1;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;

@Data
@JsonPropertyOrder({"idUsuario","nome","login","status", "idPerfil", "senha"}) //ORDEM DO JSON
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioVO extends RepresentationModel<UsuarioVO> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private long idUsuario;


    private String nome;

    private String login;

    private String senha;


    private int status;


   // ALTERANDO NOME DO CAMPO
    private long idPerfil;


    public UsuarioVO(long idUsuario, String nome, int status) {
    }



}
