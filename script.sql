-- CLIENTE
CREATE TABLE cliente(
	id_cliente SERIAL NOT NULL,
	nombre CHARACTER VARYING(50),
	apellido CHARACTER VARYING(50),
	nro_documento CHARACTER VARYING(50),
	tipo_documento CHARACTER VARYING(50),
	nacionalidad CHARACTER VARYING(50),
	email CHARACTER VARYING(50),
	telefono CHARACTER VARYING(50),
	fechaNacimiento DATE,
	CONSTRAINT pk_cliente PRIMARY KEY(id_cliente) 
);

-- VENCIMIENTO
CREATE TABLE vencimiento(
	id_vencimiento SERIAL NOT NULL,
	fecha_inicio DATE NOT NULL,
	fecha_fin DATE NOT NULL,
	duracion INTEGER NOT NULL,
	CONSTRAINT pk_vencimiento PRIMARY KEY(id_vencimiento)
);

-- regla_asignacion
CREATE TABLE regla_asignacion(
	id_regla_asignacion SERIAL NOT NULL,
	limite_inferior INTEGER,
	limite_superior INTEGER,
	monto_equivalencia INTEGER,
	CONSTRAINT pk_regla_asignacion PRIMARY KEY(id_regla_asignacion)
);

-- Concepto
CREATE TABLE concepto(
	id_concepto SERIAL NOT NULL,
	descripcion CHARACTER VARYING(100),
	puntos_requeridos INTEGER NOT NULL,
	CONSTRAINT pk_concepto PRIMARY KEY(id_concepto)
);

-- Bolsa
CREATE TABLE bolsa(
	id_bolsa SERIAL NOT NULL,
	fecha_asignacion DATE,
	fecha_caducidad DATE,
	puntaje_asignado INTEGER,
	puntaje_utilizado INTEGER,
	saldo_puntos INTEGER,
	monto_operacion INTEGER,
	estado INTEGER,
	id_cliente INTEGER NOT NULL,
  	CONSTRAINT pk_bolsa PRIMARY KEY(id_bolsa),
  	CONSTRAINT fk_bolsa_cliente FOREIGN KEY(id_cliente) REFERENCES cliente(id_cliente)
);

-- Cabecera
CREATE TABLE cabecera (
	id_cabecera SERIAL NOT NULL,
  	puntos_utilizado INTEGER,
  	fecha DATE,
  	id_cliente INTEGER NOT NULL,
  	concepto_de_uso INTEGER,
	CONSTRAINT pk_cabecera PRIMARY KEY(id_cabecera),
  	CONSTRAINT fk_cabecera_cliente FOREIGN KEY(id_cliente) REFERENCES cliente(id_cliente),
  	CONSTRAINT fk_cabecera_concepto FOREIGN KEY(concepto_de_uso) REFERENCES concepto(id_concepto)
);

-- Detalle
CREATE TABLE detalle (
	id_detalle SERIAL,
	puntaje_utilizado INTEGER,
	id_bolsa INTEGER,
	id_cabecera INTEGER,
	CONSTRAINT pk_detalle PRIMARY KEY(id_detalle),
	CONSTRAINT fk_detalle_bolsa FOREIGN KEY(id_bolsa) REFERENCES bolsa(id_bolsa),
	CONSTRAINT fk_detalle_cabecera FOREIGN KEY(id_cabecera) REFERENCES cabecera(id_cabecera)
);
