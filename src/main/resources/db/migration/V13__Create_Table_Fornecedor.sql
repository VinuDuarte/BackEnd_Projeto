CREATE TABLE  IF NOT EXISTS public.fornecedor (
            id_fornecedor int8 NOT NULL,
            nome varchar(255) NOT NULL,
            CONSTRAINT fornecedor_pkey PRIMARY KEY (id_fornecedor)
);