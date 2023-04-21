create database pruebas;

use pruebas;

create table usuarios(
	idUsuario int primary key auto_increment,
	nombre varchar(50),
	email varchar(50)
);