CREATE TABLE public.usuario (
                                id_usuario int8 NOT NULL,
                                login varchar(255) NULL,
                                nome varchar(255) NULL,
                                senha varchar(255) NULL,
                                status int4 NULL,
                                id_perfil int8 NOT NULL,
                                CONSTRAINT usuario_pkey PRIMARY KEY (id_usuario),
                                CONSTRAINT fk131gkl0dt1966rsw6dmesnsxw FOREIGN KEY (id_perfil) REFERENCES public.perfil(id_perfil)
);