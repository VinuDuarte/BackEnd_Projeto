package com.example.tutorialv2.service;

import com.example.tutorialv2.controller.ProdutoController;
import com.example.tutorialv2.data.vo.v1.ProdutoVO;
import com.example.tutorialv2.exceptions.MethodArgumentNotValidException;
import com.example.tutorialv2.exceptions.NaoEncontradoException;
import com.example.tutorialv2.mapper.DozerMapper;
import com.example.tutorialv2.model.Produto;
import com.example.tutorialv2.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    PagedResourcesAssembler<ProdutoVO> assembler;

    public PagedModel<EntityModel<ProdutoVO>> findAll(Pageable pageable) {


        var productPage = produtoRepository.findAll(pageable);

               var productVo = productPage.map(p -> DozerMapper.parseObject(p, ProdutoVO.class));

        Link  link = linkTo(methodOn(ProdutoController.class).
                getAllProdutos(pageable.getPageNumber(),
                pageable.getPageSize(),
                "asc" )).withSelfRel();

        return assembler.toModel(productVo, link);

    }

    public ProdutoVO findById(Long id) throws Exception {
        var produtoData = produtoRepository.findById(id)
                .orElseThrow(()-> new NaoEncontradoException("Produto não cadastrad"));

        return DozerMapper.parseObject(produtoData, ProdutoVO.class);
    }

//    @Bean
//    public Boolean testIgnore(){
//        Pageable.ofSize()
//
//        var productPage = produtoRepository.findByNomeIgnoreCase("test",pageable);
//        return Boolean.TRUE;
//    }
    public PagedModel<EntityModel<ProdutoVO>> buscarByNome(String nome,  Pageable pageable){


        var productPage = produtoRepository.findByNomeIgnoreCase(nome,pageable);

        var productVo = productPage.map(p -> DozerMapper.parseObject(p, ProdutoVO.class));

        Link  link = linkTo(methodOn(ProdutoController.class).
                getAllProdutos(pageable.getPageNumber(),
                        pageable.getPageSize(),
                        "asc" )).withSelfRel();

        return assembler.toModel(productVo, link);

    }



    public ProdutoVO createProduto(ProdutoVO produto) throws Exception {
            var enity = DozerMapper.parseObject(produto, Produto.class);
                var containsProduto = produto.getNome();

            Long fornecedorPresent = produtoRepository.findByIdFornecedor(enity.getIdFornecedor());

            if(fornecedorPresent == null) {
                throw new NaoEncontradoException("Fornecedor não Cadastrado"); //VALIDAR SE EXISTE FORNECEDOR NA BASE
            }

            if (produtoRepository.findByNome(containsProduto).isEmpty()){
                var vo = DozerMapper.parseObject(produtoRepository.save(enity), ProdutoVO.class);
                return vo;

            } else {
                throw new MethodArgumentNotValidException(produto.getNome() + "Já cadastrado");
            }
    }

    public ProdutoVO updateProduto(ProdutoVO produto) throws Exception {
               var produtoData = produtoRepository.findById(produto.getIdProduto())
                       .orElseThrow(() -> new NaoEncontradoException("Id não Encontrado"));

               produtoData.setNome(produto.getNome());
               produtoData.setMarca(produto.getMarca());
               produtoData.setTamanho(produto.getTamanho());
               produtoData.setPreco(produto.getPreco());
               produtoData.setImagem(produto.getImagem());
               produtoData.setStatus(produto.getStatus());
               produtoData.setQtd(produto.getQtd());
               produtoData.setIdFornecedor(produto.getIdFornecedor());

               if (produtoData.getPreco() < 0){
                   throw new MethodArgumentNotValidException("Preço não pode ser menor que R$0.00");
               }

               Long fornecedorPresent = produtoRepository.findByIdFornecedor(produtoData.getIdFornecedor());

                if(fornecedorPresent == null) {
                    throw new NaoEncontradoException("Fornecedor não Cadastrado"); //VALIDAR SE EXISTE FORNECEDOR NA BASE
                }

                var vo = DozerMapper.parseObject(produtoRepository.save(produtoData), ProdutoVO.class);
                return vo;
    }

    public void deleteProduto(Long id) throws Exception {
        var enity = produtoRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("ID ENCONTRADO"));
        produtoRepository.delete(enity);
    }




}
