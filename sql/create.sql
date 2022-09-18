create table empleados(
	id bigint not null unique auto_increment,
	rut varchar(12) not null unique,
	apellidos varchar(50) not null,
	nombres varchar(50) not null,
	categoria varchar(1) not null,
	fecha_ingreso date not null,
	fecha_nac date not null,
	primary key(id)
);

create table marcas(
	id bigint not null unique auto_increment,
	fecha date not null,
	hora time  not null
	rut_empleado varchar(12) not null,
	justificativo boolean,
	autorizacion boolean,
	primary key(id),
	foreign key(rut_empleado) references empleados(rut)
);


create table planillas(
	id bigint not null unique auto_increment,
	rut_empleado varchar(50) not null unique,
	sfm int not null,
	monto_anyos int not null,
	monto_horas int not null,
	monto_desc int not null,
	monto_final int not null,
	cot_prev int not null,
	cot_sal int not null,
	primary key(id),
	foreign key(rut_empleado) references empleados(rut)
);
