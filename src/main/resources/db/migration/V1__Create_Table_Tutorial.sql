CREATE TABLE IF NOT EXISTS public.tutorials (
                                  id int8 NOT NULL,
                                  description varchar(255) NULL,
                                  published bool NULL,
                                  title varchar(255) NULL,
                                  dt_inclusao timestamp,
                                  CONSTRAINT tutorials_pkey PRIMARY KEY (id)
);

