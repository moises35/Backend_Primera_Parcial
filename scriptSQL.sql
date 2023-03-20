-- Creación de la tabla Cliente
CREATE TABLE public.cliente(
	id_cliente INTEGER NOT NULL,
	nombre CHARACTER VARYING(50),
	apellido CHARACTER VARYING(50),
	num_documento CHARACTER VARYING(15),
	tipo_documento CHARACTER VARYING(50),
	nacionalidad CHARACTER VARYING(50),
	email CHARACTER VARYING(50),
	telefono CHARACTER VARYING(50),
	fecha_nacimiento CHARACTER VARYING(50),
	CONSTRAINT pk_cliente PRIMARY KEY (id_cliente)
);

CREATE SEQUENCE public.cliente_seq;

-- Creación de la tabla Premios
CREATE TABLE public.premios(
	id_premio INTEGER NOT NULL,
	descripcion CHARACTER VARYING(100),
	puntos_req INTEGER,
	CONSTRAINT pk_premios PRIMARY KEY (id_premio)
);

CREATE SEQUENCE public.premio_seq;

