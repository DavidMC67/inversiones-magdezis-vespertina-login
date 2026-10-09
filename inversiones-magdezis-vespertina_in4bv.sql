create database if not exists `inversiones-magdezis-vespertina_in4bv`;
use `inversiones-magdezis-vespertina_in4bv`;

create table if not exists roles (
    id_rol int auto_increment primary key,
    nombre_rol varchar(20) not null unique
);

create table if not exists usuarios (
    id_usuario int auto_increment primary key,
    nombre_usuario varchar(50) not null,
    apellido_usuario varchar(50) not null,
    correo_usuario varchar(100) not null unique,
    contrasena_usuario varchar(100) not null,
    id_rol int not null,
    foreign key (id_rol) references roles(id_rol)
);

insert ignore into roles (nombre_rol) values ('USER'), ('ADMIN');
