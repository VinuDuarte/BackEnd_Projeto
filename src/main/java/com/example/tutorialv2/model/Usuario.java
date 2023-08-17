package com.example.tutorialv2.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
//@AllArgsConstructor
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



    public Usuario(long idUsuario, String nome, String login, String senha, int status, long idPerfil) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.status = status;
        this.idPerfil = idPerfil;
    }



    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nome='" + nome + '\'' +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", status=" + status +
                ", idPerfil=" + idPerfil +
                '}';
    }
}
