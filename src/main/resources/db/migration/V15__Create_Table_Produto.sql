CREATE TABLE IF NOT EXISTS public.produto (
                                id_produto serial8 NOT NULL,
                                nome varchar(100) NULL,
                                marca varchar(20) NULL,
                                tamanho int4 NULL,
                                preco float4 NULL,
                                imagem varchar(255) NULL,
                                status int4 NULL,
                                qtd int8 NULL,
                                id_fornecedor int8 NULL,
                                CONSTRAINT produto_pkey PRIMARY KEY (id_produto),
                                CONSTRAINT produto_fk FOREIGN KEY (id_fornecedor) REFERENCES public.fornecedor(id_fornecedor)
);