package com.example.tutorialv2.data.vo.v1;


import com.example.tutorialv2.model.Usuario;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;

@Data
@JsonPropertyOrder({"idUsuario","nome","status"}) //ORDEM DO JSON

public class UsuarioEditarResponseVO {


    private long idUsuario;
    private String nome;

    private int status;

    public UsuarioVO toModel(){
        return new UsuarioVO(idUsuario,nome,status);
    }


}
