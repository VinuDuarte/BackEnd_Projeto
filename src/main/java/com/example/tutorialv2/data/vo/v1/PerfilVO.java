package com.example.tutorialv2.data.vo.v1;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PerfilVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -1712882888849948058L;

    private long idPerfil;
    private String nome;

}
