//CLEAN DB
DROP DATABASE Heineken;

//DB creation
CREATE DATABASE Heineken;

//DB selection
USE Heineken;

//Drop table
drop table "table";

//Table creator for "Lote"
CREATE TABLE Lote(
idLote varchar(5),
tarimas int NOT NULL,
cajas int NOT NULL,
cajasTarimas int NOT NULL,
PRIMARY KEY(idLote)
)
;

//Table creator for "Producto"
CREATE TABLE Producto(
idCodigo varchar(5),
PRIMARY KEY (idCodigo)
)
;

//Table creator for "Envase"
CREATE TABLE Envase(
descripcion varchar(100),
mililitros int,
PRIMARY KEY (descripcion, mililitros)
)
;

//TABLE Creator for "ProductoEnvasado"
CREATE TABLE ProductoEnvasado(
idCodigo varchar(5),
descripcion varchar(100),
mililitros int,
fechaEntrada datetime,
fechaSalida datetime,
edad int NOT NULL,
cantidadProducto int,
FOREIGN KEY (idCodigo) references Producto (idCodigo),
FOREIGN KEY (descripcion) references Envase (descripcion),
PRIMARY KEY (idCodigo, descripcion, mililitros)
)
;

CREATE TABLE Usuario(
nombreUsuario varchar(15),
contrasena varchar(20),
tipo ENUM('admin','empleado'),
PRIMARY KEY (nombreUsuario)
)
;

SQL> CREATE TABLE Employees
(
   id INT,
   age INT,
   first VARCHAR(255),
   last VARCHAR(255),
   PRIMARY KEY ( id )
);
