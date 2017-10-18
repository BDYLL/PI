#Inserts para tamira

INSERT INTO Tarima VALUES ('000001', 4,4,4,'nueva',10,100,50);
INSERT INTO Tarima VALUES ('000002', 1,2,3,'nueva',9,90,40);
INSERT INTO Tarima VALUES ('000003', 4,2,2,'nueva',8,80,30);
INSERT INTO Tarima VALUES ('000004', 10,2,3,'reciclada',1,10,1);
INSERT INTO Tarima VALUES ('000005', 4,4,4,'reciclada',7,70,20);
INSERT INTO Tarima VALUES ('000006', 3,2,1,'reciclada',5,50,10);

#Inserts para material

INSERT INTO Material VALUES ('grapa', 5.5,100,'emprendedor',123.3);
INSERT INTO Material VALUES ('grapa', 5.5,50,'emprendedor2',70);
INSERT INTO Material VALUES ('grapa', 5.5,10,'tukson',20);
INSERT INTO Material VALUES ('clavo', 10,500,'tec',200);
INSERT INTO Material VALUES ('emplayer', 20,30,'acme',4000);
INSERT INTO Material VALUES ('engrapadora', 30,10,'marca1',1500);

#inserts para madera

INSERT INTO Madera VALUES(10,10,5,50.5,5);
INSERT INTO Madera VALUES(2,2,0.5,20,3);
INSERT INTO Madera VALUES(3,10,1.2,30.2,10);
INSERT INTO Madera VALUES(1,1,0.2,10,7);
INSERT INTO Madera VALUES(1.5,1.5,0.2,10.5,3);


#inserts para proveemadera

INSERT INTO ProveeMadera VALUES('0000000000000001'
	,10,10,5,'1-1-2016');
INSERT INTO ProveeMadera VALUES('0000000000000001'
	,2,2,0.5,'2-15-2016');
INSERT INTO ProveeMadera VALUES('0000000000000003'
	,3,10,1.2,'1-23-2016');
INSERT INTO ProveeMadera VALUES('0000000000000001'
	,1,1,0.2,'12-20-2015');
INSERT INTO ProveeMadera VALUES('0000000000000003'
	,1.5,1.5,0.2,'3-10-2016');

INSERT INTO ProveeDanadas VALUES('000004','0000000000000002','3-15-2016');
INSERT INTO ProveeDanadas VALUES('000005','0000000000000002','3-15-2016');
INSERT INTO ProveeDanadas VALUES('000006','0000000000000003','3-15-2016');


#Inserts proveedor 

INSERT INTO proveedor VALUES ('0000000000000001', 
	'empresa1','av 1','colonia 1',1,11,'000001');
INSERT INTO proveedor VALUES ('0000000000000002', 
	'empresa2','av 2','colonia 2',2,22,'000002');
INSERT INTO proveedor VALUES ('0000000000000003', 
	'empresa3','av 3','colonia 3',22,1,'000003');
INSERT INTO proveedor VALUES ('0000000000000004', 
	'empresa4','av 4','colonia 4',22,1,'000004');
INSERT INTO proveedor VALUES ('0000000000000005', 
	'empresa5','av 5','colonia 5',22,1,'000005');
INSERT INTO proveedor VALUES ('0000000000000006', 
	'empresa6','av 6','colonia 6',22,1,'000006');

#nserts para contacto proveedor	

INSERT INTO ContactoProveedor VALUES 
	(SYS_GUID(), 'pedro','perez','asada','contacto')
;
INSERT INTO ContactoProveedor VALUES 
	(SYS_GUID(), 'paco','hidalgo','pastor','administrador')
;
INSERT INTO ContactoProveedor VALUES 
	(SYS_GUID(), 'diego','ramirez','hola','manager')
;
INSERT INTO ContactoProveedor VALUES 
	(SYS_GUID(), 'edgar','yañez','ramiez','contacto')
;
INSERT INTO ContactoProveedor VALUES 
	(SYS_GUID(), 'alberto','perez','gaxiola','recursos')
;
INSERT INTO ContactoProveedor VALUES 
	(SYS_GUID(), 'karen','gutierrez','ramirez','mercadotecnia')
;

#inserts de telefonos

INSERT INTO Ptelefono VALUES(36481621,'0000000000000001');
INSERT INTO Ptelefono VALUES(33901581,'0000000000000001');
INSERT INTO Ptelefono VALUES(36212190,'0000000000000002');
INSERT INTO Ptelefono VALUES(36121181,'0000000000000003');
INSERT INTO Ptelefono VALUES(36129745,'0000000000000004');
INSERT INTO Ptelefono VALUES(36900011,'0000000000000005');
INSERT INTO Ptelefono VALUES(33122001,'0000000000000006');

INSERT INTO CPTelefono VALUES(33448100,'306450FF14E30282E0530A35280AEC98');
INSERT INTO CPTelefono VALUES(33331592,'306450FF14E40282E0530A35280AEC98');
INSERT INTO CPTelefono VALUES(33449240,'306450FF14E40282E0530A35280AEC98');
INSERT INTO CPTelefono VALUES(33310821,'306450FF14E50282E0530A35280AEC98');
INSERT INTO CPTelefono VALUES(33401111,'306450FF14E60282E0530A35280AEC98');
INSERT INTO CPTelefono VALUES(33444888,'306450FF14E70282E0530A35280AEC98');
INSERT INTO CPTelefono VALUES(33555121,'306450FF14E80282E0530A35280AEC98');

#Inserts para factura tarima 
INSERT INTO FacturaTarima VALUES 
	(SYS_GUID(),'1000000000000000', '000001',2,'3-3-2016','efectivo')
;
INSERT INTO FacturaTarima VALUES 
	(SYS_GUID(),'2000000000000000', '000002',2,'4-5-2016','credito')
;
INSERT INTO FacturaTarima VALUES 
	(SYS_GUID(),'3000000000000000', '000003',2,'5-6-2016','desconocido')
;
INSERT INTO FacturaTarima VALUES 
	(SYS_GUID(),'4000000000000000', '000004',2,'7-8-2016','efectivo')
;

#Inserts para Factura material

INSERT INTO FacturaMaterial VALUES 
	(SYS_GUID(),'5000000000000000', 'grapa',5.5,1,'2-2-2015','credito')
;
INSERT INTO FacturaMaterial VALUES 
	(SYS_GUID(),'1000000000000000', 'clavo', 10,2.10,'1-2-2015','desconocido')
;
INSERT INTO FacturaMaterial VALUES 
	(SYS_GUID(),'6000000000000000', 'engrapadora', 30,5.1,'2-1-2015','credito')
;

#Inserts para cliente	

INSERT INTO Cliente 
	VALUES('1000000000000000','pedro','hernandez','hernandez',
		'calle 11','colonia 11',1,9,11111112,'durango','durango',
		'elyisus@itesm.mx')
;
INSERT INTO Cliente 
	VALUES('2000000000000000','carlos','perez','bauer',
		'calle 12','colonia 12',2,8,11111113,'nuevo leon','leon',
		'carlos@itesm.mx')
;
INSERT INTO Cliente 
	VALUES('3000000000000000','jorge','smith','salinas',
		'calle 13','colonia 13',3,7,11111114,'jalisco','zapopan',
		'jorge@itesm.mx')
;
INSERT INTO Cliente 
	VALUES('4000000000000000','perla','rodriguez','guzman',
		'calle 14','colonia 14',4,6,11111115,'jalisco','guadalajara',
		'perla@itesm.mx')
;
INSERT INTO Cliente 
	VALUES('5000000000000000','sofia','fernandez','perez',
		'calle 15','colonia 15',5,5,11111116,'nayarit','tepic',
		'sofia@itesm.mx')
;
INSERT INTO Cliente 
	VALUES('6000000000000000','aldo','yañez','hernandez',
		'calle 16','colonia 16',6,4,11111117,'chihuahua','juarez',
		'aldo@itesm.mx')
;


#Inserts para provee material

INSERT INTO ProveeMaterial 
	VALUES('0000000000000001','grapa',5.5,'6-1-2010')
;
INSERT INTO ProveeMaterial 
	VALUES('0000000000000002','clavo', 10,'2-1-2010')
;
INSERT INTO ProveeMaterial 
	VALUES('0000000000000003','emplayer', 20,'1-1-2010')
;
INSERT INTO ProveeMaterial 
	VALUES('0000000000000004','engrapadora', 30,'5-5-2010')
;


UPDATE Proveedor SET IDCP='306450FF14E30282E0530A35280AEC98'
WHERE RFCProveedor='0000000000000001';

UPDATE Proveedor SET IDCP='306450FF14E40282E0530A35280AEC98'
WHERE RFCProveedor='0000000000000002';

UPDATE Proveedor SET IDCP='306450FF14E50282E0530A35280AEC98'
WHERE RFCProveedor='0000000000000003';


UPDATE Proveedor SET IDCP='306450FF14E60282E0530A35280AEC98'
WHERE RFCProveedor='0000000000000004';

UPDATE Proveedor SET IDCP='306450FF14E70282E0530A35280AEC98'
WHERE RFCProveedor='0000000000000005';


UPDATE Proveedor SET IDCP='306450FF14E80282E0530A35280AEC98'
WHERE RFCProveedor='0000000000000006';