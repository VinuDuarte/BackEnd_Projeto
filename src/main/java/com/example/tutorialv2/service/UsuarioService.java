package com.example.tutorialv2.service;

import com.example.tutorialv2.controller.PerfilController;
import com.example.tutorialv2.data.vo.v1.UsuarioVO;
import com.example.tutorialv2.exceptions.MethodArgumentNotValidException;
import com.example.tutorialv2.exceptions.NaoEncontradoException;
import com.example.tutorialv2.mapper.DozerMapper;
import com.example.tutorialv2.model.Usuario;
import com.example.tutorialv2.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public List<UsuarioVO> findAll(String nome) throws Exception {
        List<UsuarioVO> usuario = new ArrayList<>();

        if (nome == null) {
            return DozerMapper.parseListObjects(usuarioRepository.findAll(), UsuarioVO.class);
        } else {
            return DozerMapper.parseListObjects(usuarioRepository.findByNome(nome), UsuarioVO.class);
        }
    }

    public UsuarioVO findById(long id) throws Exception {
        var usuarioData = usuarioRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Usuario não Cadastrado"));

        UsuarioVO vo = DozerMapper.parseObject(usuarioData, UsuarioVO.class); //PEGANDO O OBJ PARA RETORNA O LINK
        long idPerfil = vo.getIdPerfil();
        vo.add(linkTo(methodOn(PerfilController.class).getPerfilById(idPerfil)).withSelfRel());
        return vo;
    }

    public UsuarioVO createUsuario(UsuarioVO usuario) throws Exception {
        var enity = DozerMapper.parseObject(usuario, Usuario.class);

        Long perfilPresent = usuarioRepository.findByIdPerfil(enity.getIdPerfil());

        if (perfilPresent == null) {
            throw new NaoEncontradoException("Perfil não Cadastrado");
        }

        var vo = DozerMapper.parseObject(usuarioRepository.save(enity), UsuarioVO.class);
        return vo;
    }

    public UsuarioVO inativarOuAtivarUsuario(UsuarioVO usuario) throws Exception {

        var usuarioData = usuarioRepository.findById(usuario.getIdUsuario())
                    .orElseThrow(() -> new NaoEncontradoException("Usuário não encontrado"));

            usuarioData.setStatus(usuario.getStatus());

            if ((usuarioData.getStatus() != 0) && (usuarioData.getStatus() != 1)){
                throw  new NaoEncontradoException("Status invalio");
            }

            var dto = DozerMapper.parseObject(usuarioRepository.save(usuarioData), UsuarioVO.class);
            return dto;
    }


    public void deleteUsuario(Long id) throws Exception {
        var enity = usuarioRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("ID ENCONTRADO"));
        usuarioRepository.delete(enity);
    }

}
