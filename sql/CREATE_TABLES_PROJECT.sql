CREATE TABLE Cliente(
	RFCCliente 		CHAR(16) CONSTRAINT pk_cliente PRIMARY KEY,
	Cnombre 		VARCHAR(30) CONSTRAINT not_null_nombre NOT NULL,
	CApellidoP 		VARCHAR(30) CONSTRAINT not_null_apellidoP NOT NULL,
	CApellidoM 		VARCHAR(30) CONSTRAINT not_null_apellidoM NOT NULL,
	CCalle 			VARCHAR(30) CONSTRAINT not_null_calle NOT NULL,
	Colonia 		VARCHAR(30) CONSTRAINT not_null_colonia NOT NULL,
	noExt 			INTEGER CONSTRAINT valid_noExt NOT NULL CHECK(noExt>0),
	noInt 			INTEGER CONSTRAINT valid_noInt NOT NULL CHECK(noInt>0),
	Telefono 		INTEGER CONSTRAINT valid_tel NOT NULL CHECK(Telefono>0),
	Estado 			VARCHAR(20) CONSTRAINT valid_estado NOT NULL,
	Municipio 		VARCHAR(30) CONSTRAINT valid_municipio NOT NULL,
	email 			VARCHAR(50) CONSTRAINT valid_email CHECK(email LIKE('%@%.%'))
);

CREATE TABLE Tarima(
 TLote	            VARCHAR(6) constraint tarima_pk PRIMARY KEY,
 TAlto              INT constraint TAlto_chk check(TAlto >= 1),
 TLargo        		INT constraint TLargo_chk check(TLargo >= 1),
 TAncho         	INT constraint TAncho_chk check(TAncho >= 1),
 TTipo        		VARCHAR(10) constraint tipo_chk check(TTipo IN ('nueva', 'reciclada')),
 TCantidad          INT constraint TCantidad_chk check(TCantidad >= 1),
 TPrecio            VARCHAR(10),
 CantidadMadera     INT constraint CantidadMadera_chk check(CantidadMadera >= 1)
);


CREATE TABLE ContactoProveedor(
	IDCP 			RAW(32) CONSTRAINT pk_cp PRIMARY KEY,
	CPNombre 		VARCHAR(30) CONSTRAINT not_null_cpnombre NOT NULL,
	CPApellidoP 	VARCHAR(30) CONSTRAINT not_null_cpapellidoP NOT NULL,
	CPApellidoM 	VARCHAR(30) CONSTRAINT not_null_cpapellidoM NOT NULL,
	CPPuesto 		VARCHAR(30) CONSTRAINT not_null_cppuesto NOT NULL
);

CREATE TABLE Proveedor
(RFCProveedor	   CHAR(16) constraint proveedor_pk PRIMARY KEY,
 PNombre           VARCHAR(30) NOT NULL,
 PCalle        	   VARCHAR(30) NOT NULL,
 PColonia          VARCHAR(30) NOT NULL,
 PnoExt        	   INT constraint PnoExt_chk check(PnoExt >= 1) NOT NULL,
 PnoInt            INT constraint PnoInt_chk check(PnoInt >= 1) NOT NULL,
 IDCP              VARCHAR(6) constraint contactoProveedor_fk  REFERENCES ContactoProveedor(IDCP)
);

CREATE TABLE Material
(MATipo	           VARCHAR(20) constraint matipo_chk check(MATipo IN ('clavo', 'grapa','engrapadora','emplayer')),
 MATamano          FLOAT constraint MATamano_chk check(MATamano >= 1) NOT NULL,
 MATCantidad       INT constraint MATCantidad_chk check(MATCantidad >= 1) NOT NULL,
 MATMarca          VARCHAR(20) constraint not_null_matmarca NOT NULL,
 MATPrecio         FLOAT constraint MATPrecio_chk check(MATPrecio >= 1) NOT NULL,
 CONSTRAINT material_pk PRIMARY KEY (MATipo, MATamano)
);

#faltan inserts
CREATE TABLE Madera(
	MADAlto 		FLOAT CONSTRAINT valid_madalto CHECK(MADAlto>0) NOT NULL,
	MADLargo 		FLOAT CONSTRAINT valid_madlargo CHECK(MADLargo>0) NOT NULL,
	MADGrosor 		FLOAT CONSTRAINT valid_madgrosor CHECK(MADGrosor>0) NOT NULL,
	MADPrecio 		FLOAT CONSTRAINT valid_madprecio CHECK(MADPrecio>0) NOT NULL,
	MADCantidad 	INT CONSTRAINT valid_madcantidad CHECK(MADCantidad>0) NOT NULL,
	CONSTRAINT pk_madera PRIMARY KEY(MADAlto,MADLargo,MADGrosor)
);

CREATE TABLE FacturaTarima(
	FTID				RAW(32),
	RFCCliente			CHAR(16) CONSTRAINT pk_facTar REFERENCES Cliente(RFCCliente) NOT NULL,		
	TLote				VARCHAR(6) CONSTRAINT TLote_fk REFERENCES tarima(TLote) NOT NULL,
	FTMonto				INT CONSTRAINT FTMonto_chk check(FTMonto>=1),
	FTFechaDeCompra		DATE CONSTRAINT not_null_ftfecha NOT NULL,
	FTFormaDePago		VARCHAR(13) CONSTRAINT FTFpago_chk check(FTFormaDePago IN ('efectivo', 'credito','desconocido')),
	CONSTRAINT pk_ft PRIMARY KEY(FTID)
);

CREATE TABLE FacturaMaterial(
	FMATID				RAW(32),
	RFCCliente 			CHAR(16) NOT NULL,
	MATipo 				VARCHAR(20) NOT NULL,
	MATamano 			FLOAT,
	FMMonto 			FLOAT CONSTRAINT valid_fmmmonto CHECK(FMMonto>0),
	FMFechaDePago 		DATE CONSTRAINT not_null_fm_date NOT NULL,
	FMFormaDePago 		VARCHAR(12) CONSTRAINT valid_fmfdp CHECK(FMFormaDePago IN('efectivo','credito','desconocido')),
	CONSTRAINT pk_fm PRIMARY KEY(FMATID),
	CONSTRAINT fk_fm_cliente FOREIGN KEY(RFCCliente) REFERENCES Cliente(RFCCliente),
	CONSTRAINT fk_fm_material FOREIGN KEY(MATipo,MATamano) REFERENCES Material(MATipo,MATamano)
);

CREATE TABLE ProveeMaterial(
	RFCProveedor 		CHAR(16),
	MATipo 				VARCHAR(20),
	MATamano 			FLOAT,
	PMATUltimaFecha 	DATE CONSTRAINT not_null_PMATUF NOT NULL,
	CONSTRAINT pk_pmat PRIMARY KEY(RFCProveedor,MATipo,MATamano),
	CONSTRAINT fk_pmat_prov FOREIGN KEY(RFCProveedor) REFERENCES Proveedor(RFCProveedor),
	CONSTRAINT fk_pmat_mat FOREIGN KEY(MATipo,MATamano) REFERENCES Material(MATipo,MATamano)
);

#falta inserts

CREATE TABLE ProveeMadera(
	RFCProveedor 		CHAR(16),
	MADAlto 			FLOAT,
	MADLargo 			FLOAT,
	MADGrosor 			FLOAT,
	PMADUltimaFecha 	DATE CONSTRAINT not_null_PMADUF NOT NULL,
	CONSTRAINT pk_pmad PRIMARY KEY(RFCProveedor,MADAlto,MADLargo,MADGrosor),
	CONSTRAINT fk_pmad_prov FOREIGN KEY(RFCProveedor) REFERENCES Proveedor(RFCProveedor),
	CONSTRAINT fk_pmad_mad FOREIGN KEY(MADAlto,MADLargo,MADGrosor) REFERENCES Madera(MADAlto,MADLargo,MADGrosor)
);

#falta inserts

CREATE TABLE ProveeDanadas(
	TLote 			VARCHAR(6),
	RFCProveedor 	CHAR(16),
		PDUltimaFecha 	DATE CONSTRAINT not_null_pduf NOT NULL,
	CONSTRAINT pk_pd PRIMARY KEY(TLote,RFCProveedor),
	CONSTRAINT fk_pd_tarima FOREIGN KEY(TLote) REFERENCES Tarima(TLote),
	CONSTRAINT fk_pd_prov FOREIGN KEY(RFCProveedor) REFERENCES Proveedor(RFCProveedor)
);

#falta inserts

CREATE TABLE PTelefono(
	PTel INTEGER CONSTRAINT valid_ptel CHECK(PTel>0),
	RFCProveedor CHAR(16),
	CONSTRAINT pk_ptel PRIMARY KEY(PTel,RFCProveedor),
	CONSTRAINT fk_ptel_prov FOREIGN KEY(RFCProveedor) REFERENCES Proveedor(RFCProveedor)
);

#falta inserts

CREATE TABLE CPTelefono(
	CPTel INT CONSTRAINT valid_cptel CHECK(CPTel>0),
	IDCP RAW(32),
	CONSTRAINT pk_cptel PRIMARY KEY(CPTel,IDCP),
	CONSTRAINT fk_cptel_cp FOREIGN KEY(IDCP) REFERENCES ContactoProveedor(IDCP) 
);

CREATE TABLE Usuario(
	nombreUsuario 	varchar(20) PRIMARY KEY,
	contrasena 		VARCHAR(30),
	tipo 			VARCHAR(13),
	CONSTRAINT valid_usuario CHECK(tipo IN('empleado','administrador'))
);

INSERT INTO Usuario VALUES('Dyanez','pruebaContrasena','administrador');

GRANT ALL ON FacturaMaterial TO a01221672;
GRANT ALL ON FacturaMaterial TO a01261902;



ALTER TABLE ContactoProveedor ADD RFCProveedor CHAR(16);
ALTER TABLE ContactoProveedor ADD CONSTRAINT proveedor_fk FOREIGN KEY(RFCProveedor) REFERENCES Proveedor(RFCProveedor);


UPDATE ContactoProveedor SET RFCProveedor='0000000000000001' WHERE IDCP='3064E129AE8009E5E0530A35280A1877';

UPDATE ContactoProveedor SET RFCProveedor='0000000000000001' WHERE IDCP='30748D0B203E1D72E0530A35280A6CCC';

UPDATE ContactoProveedor SET RFCProveedor='0000000000000002' WHERE IDCP='306450FF14E30282E0530A35280AEC98';

UPDATE ContactoProveedor SET RFCProveedor='0000000000000003' WHERE IDCP='306450FF14E40282E0530A35280AEC98';

UPDATE ContactoProveedor SET RFCProveedor='0000000000000005' WHERE IDCP='306450FF14E50282E0530A35280AEC98';

UPDATE ContactoProveedor SET RFCProveedor='0000000000000004' WHERE IDCP='306450FF14E60282E0530A35280AEC98';

UPDATE ContactoProveedor SET RFCProveedor='0000000000000006' WHERE IDCP='306450FF14E30282E0530A35280AEC98';

UPDATE ContactoProveedor SET RFCProveedor='0000000000000002' WHERE IDCP='306450FF14E70282E0530A35280AEC98';

UPDATE ContactoProveedor SET RFCProveedor='ABCDEF0123456789' WHERE IDCP='306450FF14E80282E0530A35280AEC98';

select idcp, rfcproveedor from contactoproveedor;