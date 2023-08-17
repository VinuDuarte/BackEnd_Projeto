package com.example.tutorialv2.service;

import com.example.tutorialv2.data.vo.v1.FornecedorVO;
import com.example.tutorialv2.exceptions.MethodArgumentNotValidException;
import com.example.tutorialv2.exceptions.NaoEncontradoException;
import com.example.tutorialv2.mapper.DozerMapper;
import com.example.tutorialv2.model.Fornecedor;
import com.example.tutorialv2.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FornecedorService {

    @Autowired
    FornecedorRepository fornecedorRepository;

    public List<FornecedorVO> findAll(String nome) throws Exception {
        List<FornecedorVO> fornecedor = new ArrayList<>();
        if(nome == null)
            return DozerMapper.parseListObjects(fornecedorRepository.findAll(), FornecedorVO.class);

        return DozerMapper.parseListObjects(fornecedorRepository.findByNome(nome), FornecedorVO.class);
    }

    public FornecedorVO findById(Long idFornecedor) throws Exception{
            var fornedorData = fornecedorRepository.findById(idFornecedor)
                    .orElseThrow(() -> new NaoEncontradoException("Fornecedor não Encontrado"));

            return DozerMapper.parseObject(fornedorData, FornecedorVO.class);
    }

    public FornecedorVO createFornecedor(FornecedorVO fornecedor) throws Exception {
        var enity = DozerMapper.parseObject(fornecedor, Fornecedor.class);
            var containsF = fornecedor.getNome();

          if (fornecedorRepository.findByNome(containsF).isEmpty()) {
              var VO = DozerMapper.parseObject(fornecedorRepository.save(enity), FornecedorVO.class);
              return VO;
          } else {
              throw new MethodArgumentNotValidException(fornecedor.getNome() + " Já cadastrado!");
          }


    }

    public FornecedorVO updateFornecedor(FornecedorVO fornecedor) throws Exception{
            long idFornecedor = fornecedor.getIdFornecedor();
            var fornecedorData = fornecedorRepository.findById(idFornecedor)
                    .orElseThrow(() -> new NaoEncontradoException("ID Não Encontrado"));

            fornecedorData.setNome(fornecedor.getNome());

            return DozerMapper.parseObject(fornecedorRepository.save(fornecedorData), FornecedorVO.class);
    }

    public void deleteFornecedor(Long idFornecedor) throws Exception {
            var enity = fornecedorRepository.findById(idFornecedor)
                    .orElseThrow(() -> new NaoEncontradoException("ID Não Encontrado"));
                fornecedorRepository.delete(enity);
    }




}
