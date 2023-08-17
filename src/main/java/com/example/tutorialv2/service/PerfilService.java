package com.example.tutorialv2.service;

import com.example.tutorialv2.data.vo.v1.PerfilVO;
import com.example.tutorialv2.exceptions.NaoEncontradoException;
import com.example.tutorialv2.mapper.DozerMapper;
import com.example.tutorialv2.model.Perfil;
import com.example.tutorialv2.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PerfilService {


    @Autowired
    private PerfilRepository perfilRepository;
    public List<PerfilVO> findAll(String nome) throws Exception {
        List<PerfilVO> perfil = new ArrayList<>();

        if (nome == null){

            return DozerMapper.parseListObjects(perfilRepository.findAll(), PerfilVO.class);
        }else {
            //tutorials.addAll(tutorialRepository.findByTitleContaining(title));
            return DozerMapper.parseListObjects(perfilRepository.findByNome(nome),PerfilVO.class);
        }
    }
    
    public PerfilVO findById(Long id) throws Exception {
        var perfilData = perfilRepository.findById(id)
                .orElseThrow(()-> new NaoEncontradoException("Perfil não Encontrado"));

            return DozerMapper.parseObject(perfilData, PerfilVO.class);

    }

    public  PerfilVO createPerfil(PerfilVO perfil) throws Exception{

        var entity = DozerMapper.parseObject(perfil, Perfil.class);

        var vo = DozerMapper.parseObject(perfilRepository.save(entity), PerfilVO.class);
        return vo;
    }

    public void deletePerfil(Long id) throws Exception {
        var enity = perfilRepository.findById(id).
                orElseThrow(() -> new NaoEncontradoException("ID NÃO ENCONTRADO"));
            perfilRepository.delete(enity);
    }
    
    



}
