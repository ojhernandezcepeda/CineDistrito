

DROP TABLE IF EXISTS BoletaBonus CASCADE
;

DROP TABLE IF EXISTS Calificacion CASCADE
;

DROP TABLE IF EXISTS Cliente CASCADE
;

DROP TABLE IF EXISTS CompraBoletas CASCADE
;

DROP TABLE IF EXISTS CompraSnacks CASCADE
;

DROP TABLE IF EXISTS CompraSnacks_Snack CASCADE
;

DROP TABLE IF EXISTS Empleado CASCADE
;

DROP TABLE IF EXISTS Establecimiento CASCADE
;

DROP TABLE IF EXISTS HistorialBoletas CASCADE
;

DROP TABLE IF EXISTS HistorialCargos CASCADE
;

DROP TABLE IF EXISTS HistorialSnacks CASCADE
;

DROP TABLE IF EXISTS InventarioGeneral CASCADE
;

DROP TABLE IF EXISTS Pelicula CASCADE
;

DROP TABLE IF EXISTS Pelicula_Silla CASCADE
;

DROP TABLE IF EXISTS ReservasPelicula CASCADE
;

DROP TABLE IF EXISTS Sala CASCADE
;

DROP TABLE IF EXISTS Silla CASCADE
;

DROP TABLE IF EXISTS Snack CASCADE
;

DROP TABLE IF EXISTS TipoEstablecimiento CASCADE
;

DROP TABLE IF EXISTS TipoSilla CASCADE
;

/* Create Tables */

CREATE TABLE BoletaBonus
(
	ID_BoletaBonus serial NOT NULL,
	Cliente_ID integer NOT NULL,
	Fecha date NOT NULL,
	FechaLimite date NOT NULL
)
;

CREATE TABLE Calificacion
(
	ID_Calificacion serial NOT NULL,
	Puntuacion integer NOT NULL,
	Pelicula_ID integer NULL,
	Snack_ID integer NULL
)
;

CREATE TABLE Cliente
(
	ID_Cliente serial NOT NULL,
	Nombre varchar(50) NOT NULL,
	Documento varchar(50) NOT NULL,
	TipoDocumento varchar(50) NOT NULL,
	Telefono varchar(50) NOT NULL,
	Contraseña varchar(50) NOT NULL,
	MaxSillas integer NOT NULL,
	TotalPuntos integer NOT NULL,
	Usuario varchar(50) NOT NULL
)
;

CREATE TABLE CompraBoletas
(
	ID_CompraBoletas serial NOT NULL,
	Fecha date NOT NULL,
	CantidadBonus integer NOT NULL,
	Empleado_ID integer NOT NULL,
	Cliente_ID integer NULL
)
;

CREATE TABLE CompraSnacks
(
	ID_CompraSnacks serial NOT NULL,
	Fecha date NOT NULL,
	Cliente_ID integer NULL,
	Empleado_ID integer NOT NULL
)
;

CREATE TABLE CompraSnacks_Snack
(
	ID_CompraSnacks_Snack serial NOT NULL,
	Snack_ID integer NOT NULL,
	CompraSnacks_ID integer NOT NULL
)
;

CREATE TABLE Empleado
(
	ID_Empleado serial NOT NULL,
	Codigo varchar(50) NOT NULL,
	Nombre varchar(50) NOT NULL,
	Cargo varchar(50) NOT NULL,
	Documento varchar(50) NOT NULL,
	TipoDocumento varchar(50) NOT NULL,
	Telefono varchar(50) NOT NULL,
	Contraseña varchar(50) NOT NULL,
	Establecimiento_ID integer NULL,
	FechaInicioContrato date NOT NULL,
	Salario numeric(10,2) NOT NULL,
	Estado varchar(50) NOT NULL
)
;

CREATE TABLE Establecimiento
(
	ID_Establecimiento serial NOT NULL,
	Nombre varchar(50) NOT NULL,
	Ubicacion varchar(50) NOT NULL,
	Max_Empleados integer NOT NULL,
	TipoEstablecimiento_ID integer NOT NULL,
	Estado varchar(50) NOT NULL
)
;

CREATE TABLE HistorialBoletas
(
	ID_HistorialBoletas serial NOT NULL,
	Compra_ID integer NOT NULL,
	CantidadBoletas integer NOT NULL,
	ValorTotal numeric(10,2) NOT NULL,
	CantidadPreferencial integer NOT NULL,
	CantidadGeneral integer NOT NULL,
	FechaVenta date NOT NULL,
	NombrePelicula varchar(200) NOT NULL,
	GeneroPelicula varchar(50) NOT NULL,
	Empleado_ID integer NOT NULL
)
;

CREATE TABLE HistorialCargos
(
	ID_HistorialCargos serial NOT NULL,
	Empleado_ID serial NOT NULL,
	Codigo varchar(50) NOT NULL,
	Cargo varchar(50) NOT NULL,
	FechaInicioContrato date NOT NULL,
	NombreEstablecimiento varchar(50) NOT NULL,
	Establecimiento_ID integer NOT NULL,
	Salario numeric(10,2) NOT NULL,
	FechaFinContrato date NULL
)
;

CREATE TABLE HistorialSnacks
(
	ID_HistorialSnacks serial NOT NULL,
	CompraSnack_ID integer NOT NULL,
	TotalSnacks integer NOT NULL,
	ValorTotal numeric(10,2) NOT NULL,
	Establecimiento_ID integer NOT NULL,
	Empleado_ID integer NOT NULL,
	FechaVenta date NOT NULL
)
;

CREATE TABLE InventarioGeneral
(
	ID_Snack serial NOT NULL,
	Nombre varchar(50) NOT NULL,
	Stock integer NOT NULL,
	ValorUnidad numeric(8,2) NOT NULL,
	Puntos integer NOT NULL,
	FechaVencimiento date NOT NULL
)
;

CREATE TABLE Pelicula
(
	ID_Pelicula serial NOT NULL,
	Nombre varchar(200) NOT NULL,
	Duracion integer NOT NULL,
	Genero varchar(50) NOT NULL,
	FechaInicio date NOT NULL,
	HoraInicio integer NOT NULL,
	Sala_ID integer NOT NULL,
	Estado varchar(50) NOT NULL
)
;

CREATE TABLE Pelicula_Silla
(
	ID_Pelicula_Silla serial NOT NULL,
	Pelicula_ID integer NOT NULL,
	Silla_ID integer NOT NULL,
	Compra_ID integer NULL,
	Reserva_ID integer NULL
)
;

CREATE TABLE ReservasPelicula
(
	ID_Reserva serial NOT NULL,
	Cliente_ID integer NOT NULL,
	Fecha date NOT NULL,
	Estado varchar(50) NOT NULL,
	valortotal numeric(10,2) NOT NULL,
	puntostotal integer NOT NULL
)
;

CREATE TABLE Sala
(
	ID_Sala serial NOT NULL,
	CantidadGeneral integer NOT NULL,
	CantidadPreferencial integer NOT NULL,
	NumeroSala integer NOT NULL,
	Establecimiento_ID integer NOT NULL,
	Estado varchar(50) NOT NULL
)
;

CREATE TABLE Silla
(
	ID_Silla serial NOT NULL,
	Fila varchar(50) NOT NULL,
	Columna integer NOT NULL,
	TipoSilla_ID integer NOT NULL
)
;

CREATE TABLE Snack
(
	ID_Snacks serial NOT NULL,
	Establecimiento_ID integer NOT NULL,
	StockEstablecimiento integer NOT NULL,
	InventarioGeneral_ID integer NOT NULL
)
;

CREATE TABLE TipoEstablecimiento
(
	ID_TipoEstablecimiento serial NOT NULL,
	Tipo varchar(50) NOT NULL
)
;

CREATE TABLE TipoSilla
(
	ID_TipoSilla serial NOT NULL,
	Nombre varchar(50) NOT NULL,
	Precio numeric(8,2) NOT NULL,
	Puntos integer NOT NULL
)
;

/* Create Primary Keys, Indexes, Uniques, Checks */

ALTER TABLE BoletaBonus ADD CONSTRAINT PK_BoletaBonus
	PRIMARY KEY (ID_BoletaBonus)
;

CREATE INDEX IXFK_BoletaBonus_Cliente ON BoletaBonus (Cliente_ID ASC)
;

ALTER TABLE Calificacion ADD CONSTRAINT PK_Calificacion
	PRIMARY KEY (ID_Calificacion)
;

CREATE INDEX IXFK_Calificacion_Pelicula ON Calificacion (Pelicula_ID ASC)
;

CREATE INDEX IXFK_Calificacion_Snack ON Calificacion (Snack_ID ASC)
;

ALTER TABLE Cliente ADD CONSTRAINT PK_Cliente
	PRIMARY KEY (ID_Cliente)
;

ALTER TABLE Cliente 
  ADD CONSTRAINT UK_Cliente_Usuario UNIQUE (Usuario)
;

ALTER TABLE CompraBoletas ADD CONSTRAINT PK_CompraBoletas
	PRIMARY KEY (ID_CompraBoletas)
;

CREATE INDEX IXFK_CompraBoletas_Cliente ON CompraBoletas (Cliente_ID ASC)
;

CREATE INDEX IXFK_CompraBoletas_Empleado ON CompraBoletas (Empleado_ID ASC)
;

ALTER TABLE CompraSnacks ADD CONSTRAINT PK_CompraSnacks
	PRIMARY KEY (ID_CompraSnacks)
;

CREATE INDEX IXFK_CompraSnacks_Cliente ON CompraSnacks (Cliente_ID ASC)
;

CREATE INDEX IXFK_CompraSnacks_Empleado ON CompraSnacks (Empleado_ID ASC)
;

ALTER TABLE CompraSnacks_Snack ADD CONSTRAINT PK_CompraSnacks_Snacks
	PRIMARY KEY (ID_CompraSnacks_Snack)
;

CREATE INDEX IXFK_CompraSnacks_Snack_CompraSnacks ON CompraSnacks_Snack (CompraSnacks_ID ASC)
;

CREATE INDEX IXFK_CompraSnacks_Snack_Snack ON CompraSnacks_Snack (Snack_ID ASC)
;

ALTER TABLE Empleado ADD CONSTRAINT PK_Empleado
	PRIMARY KEY (ID_Empleado)
;

ALTER TABLE Empleado 
  ADD CONSTRAINT UK_Empleado_Codigo UNIQUE (Codigo)
;

CREATE INDEX IXFK_Empleado_Establecimiento ON Empleado (Establecimiento_ID ASC)
;

ALTER TABLE Establecimiento ADD CONSTRAINT PK_Establecimiento
	PRIMARY KEY (ID_Establecimiento)
;

CREATE INDEX IXFK_Establecimiento_TipoEstablecimiento ON Establecimiento (TipoEstablecimiento_ID ASC)
;

ALTER TABLE HistorialBoletas ADD CONSTRAINT PK_Auditoria1
	PRIMARY KEY (ID_HistorialBoletas)
;

ALTER TABLE HistorialCargos ADD CONSTRAINT PK_HistorialCargos
	PRIMARY KEY (ID_HistorialCargos)
;

ALTER TABLE HistorialSnacks ADD CONSTRAINT PK_Auditoria2
	PRIMARY KEY (ID_HistorialSnacks)
;

ALTER TABLE InventarioGeneral ADD CONSTRAINT PK_InventarioGeneral
	PRIMARY KEY (ID_Snack)
;

ALTER TABLE Pelicula ADD CONSTRAINT PK_Pelicula
	PRIMARY KEY (ID_Pelicula)
;

CREATE INDEX IXFK_Pelicula_Sala ON Pelicula (Sala_ID ASC)
;

ALTER TABLE Pelicula_Silla ADD CONSTRAINT PK_Pelicula_Silla
	PRIMARY KEY (ID_Pelicula_Silla)
;

CREATE INDEX IXFK_Pelicula_Silla_CompraBoletas ON Pelicula_Silla (Compra_ID ASC)
;

CREATE INDEX IXFK_Pelicula_Silla_Pelicula ON Pelicula_Silla (Pelicula_ID ASC)
;

CREATE INDEX IXFK_Pelicula_Silla_ReservasPelicula ON Pelicula_Silla (Reserva_ID ASC)
;

CREATE INDEX IXFK_Pelicula_Silla_Silla ON Pelicula_Silla (Silla_ID ASC)
;

ALTER TABLE ReservasPelicula ADD CONSTRAINT PK_ReservasPelicula
	PRIMARY KEY (ID_Reserva)
;

CREATE INDEX IXFK_ReservasPelicula_Cliente ON ReservasPelicula (Cliente_ID ASC)
;

ALTER TABLE Sala ADD CONSTRAINT PK_Sala
	PRIMARY KEY (ID_Sala)
;

CREATE INDEX IXFK_Sala_Establecimiento ON Sala (Establecimiento_ID ASC)
;

ALTER TABLE Silla ADD CONSTRAINT PK_Silla
	PRIMARY KEY (ID_Silla)
;

CREATE INDEX IXFK_Silla_TipoSilla ON Silla (TipoSilla_ID ASC)
;

ALTER TABLE Snack ADD CONSTRAINT PK_Snacks
	PRIMARY KEY (ID_Snacks)
;

CREATE INDEX IXFK_Snacks_Establecimiento ON Snack (Establecimiento_ID ASC)
;

CREATE INDEX IXFK_Snacks_InventarioGeneral ON Snack (InventarioGeneral_ID ASC)
;

ALTER TABLE TipoEstablecimiento ADD CONSTRAINT PK_TipoEstablecimiento
	PRIMARY KEY (ID_TipoEstablecimiento)
;

ALTER TABLE TipoSilla ADD CONSTRAINT PK_TipoSilla
	PRIMARY KEY (ID_TipoSilla)
;

/* Create Foreign Key Constraints */

ALTER TABLE BoletaBonus ADD CONSTRAINT FK_BoletaBonus_Cliente
	FOREIGN KEY (Cliente_ID) REFERENCES Cliente (ID_Cliente) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE Calificacion ADD CONSTRAINT FK_Calificacion_Pelicula
	FOREIGN KEY (Pelicula_ID) REFERENCES Pelicula (ID_Pelicula) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE Calificacion ADD CONSTRAINT FK_Calificacion_Snack
	FOREIGN KEY (Snack_ID) REFERENCES Snack (ID_Snacks) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE CompraBoletas ADD CONSTRAINT FK_CompraBoletas_Cliente
	FOREIGN KEY (Cliente_ID) REFERENCES Cliente (ID_Cliente) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE CompraBoletas ADD CONSTRAINT FK_CompraBoletas_Empleado
	FOREIGN KEY (Empleado_ID) REFERENCES Empleado (ID_Empleado) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE CompraSnacks ADD CONSTRAINT FK_CompraSnacks_Cliente
	FOREIGN KEY (Cliente_ID) REFERENCES Cliente (ID_Cliente) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE CompraSnacks ADD CONSTRAINT FK_CompraSnacks_Empleado
	FOREIGN KEY (Empleado_ID) REFERENCES Empleado (ID_Empleado) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE CompraSnacks_Snack ADD CONSTRAINT FK_CompraSnacks_Snack_CompraSnacks
	FOREIGN KEY (CompraSnacks_ID) REFERENCES CompraSnacks (ID_CompraSnacks) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE CompraSnacks_Snack ADD CONSTRAINT FK_CompraSnacks_Snack_Snack
	FOREIGN KEY (Snack_ID) REFERENCES Snack (ID_Snacks) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE Empleado ADD CONSTRAINT FK_Empleado_Establecimiento
	FOREIGN KEY (Establecimiento_ID) REFERENCES Establecimiento (ID_Establecimiento) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE Establecimiento ADD CONSTRAINT FK_Establecimiento_TipoEstablecimiento
	FOREIGN KEY (TipoEstablecimiento_ID) REFERENCES TipoEstablecimiento (ID_TipoEstablecimiento) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE Pelicula ADD CONSTRAINT FK_Pelicula_Sala
	FOREIGN KEY (Sala_ID) REFERENCES Sala (ID_Sala) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE Pelicula_Silla ADD CONSTRAINT FK_Pelicula_Silla_CompraBoletas
	FOREIGN KEY (Compra_ID) REFERENCES CompraBoletas (ID_CompraBoletas) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE Pelicula_Silla ADD CONSTRAINT FK_Pelicula_Silla_Pelicula
	FOREIGN KEY (Pelicula_ID) REFERENCES Pelicula (ID_Pelicula) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE Pelicula_Silla ADD CONSTRAINT FK_Pelicula_Silla_ReservasPelicula
	FOREIGN KEY (Reserva_ID) REFERENCES ReservasPelicula (ID_Reserva) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE Pelicula_Silla ADD CONSTRAINT FK_Pelicula_Silla_Silla
	FOREIGN KEY (Silla_ID) REFERENCES Silla (ID_Silla) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE ReservasPelicula ADD CONSTRAINT FK_ReservasPelicula_Cliente
	FOREIGN KEY (Cliente_ID) REFERENCES Cliente (ID_Cliente) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE Sala ADD CONSTRAINT FK_Sala_Establecimiento
	FOREIGN KEY (Establecimiento_ID) REFERENCES Establecimiento (ID_Establecimiento) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE Silla ADD CONSTRAINT FK_Silla_TipoSilla
	FOREIGN KEY (TipoSilla_ID) REFERENCES TipoSilla (ID_TipoSilla) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE Snack ADD CONSTRAINT FK_Snacks_Establecimiento
	FOREIGN KEY (Establecimiento_ID) REFERENCES Establecimiento (ID_Establecimiento) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE Snack ADD CONSTRAINT FK_Snacks_InventarioGeneral
	FOREIGN KEY (InventarioGeneral_ID) REFERENCES InventarioGeneral (ID_Snack) ON DELETE No Action ON UPDATE No Action
;

/* Create Table Comments, Sequences for Autonumber Columns */

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 
