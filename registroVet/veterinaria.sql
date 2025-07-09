create database veterinaria;

use veterinaria;

CREATE TABLE personas (
    documento VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(100),
    telefono VARCHAR(20)
);

CREATE TABLE mascotas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50),
    id_dueno VARCHAR(20),
    raza VARCHAR(50),
    sexo VARCHAR(10),
    FOREIGN KEY (id_dueno) REFERENCES personas(documento)
);
