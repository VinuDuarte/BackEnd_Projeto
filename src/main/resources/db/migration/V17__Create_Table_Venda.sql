CREATE table if not exists  public.venda (
                                             id_venda int8 NOT NULL,
                                             datavenda date NOT NULL,
                                             id_usuario int8 NOT NULL,
                                             exibir bool NULL DEFAULT true,
                                             id_produto int8 NOT NULL,
                                             quantidade_produto int8 NULL,
                                             valor_venda numeric NULL,
                                             CONSTRAINT venda_pk PRIMARY KEY (id_venda),
                                             CONSTRAINT venda_produto_fk FOREIGN KEY (id_produto) REFERENCES public.produto(id_produto),
                                             CONSTRAINT venda_usuario_fk FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario)
);