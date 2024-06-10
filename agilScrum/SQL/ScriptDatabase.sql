-- Seleccionar el esquema de base de datos
USE agil_scrum;

-- Crear tabla Rol
CREATE TABLE Rol (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    codigo VARCHAR(50) NOT NULL
);

-- Crear tabla Permiso
CREATE TABLE Permiso (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    codigo VARCHAR(50) NOT NULL
);

-- Crear tabla Accion
CREATE TABLE Accion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    codigo VARCHAR(50) NOT NULL
);

-- Crear tabla Usuario
CREATE TABLE Usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL,
    mail VARCHAR(255) NOT NULL,
    usuario VARCHAR(255) NOT NULL,
    contraseña VARCHAR(255) NOT NULL,
    rol_id INT,
    FOREIGN KEY (rol_id) REFERENCES Rol(id)
);


-- Crear tabla Estado
CREATE TABLE Estado (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    codigo VARCHAR(50) NOT NULL
);

-- Crear tabla Prioridad
CREATE TABLE Prioridad (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    codigo VARCHAR(50) NOT NULL
);

-- Crear tabla Proyecto
CREATE TABLE Proyecto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    fechaInicio DATE NOT NULL,
    fechaFin DATE,
    estado_id INT,
    FOREIGN KEY (estado_id) REFERENCES Estado(id)
);

-- Crear tabla Sprint
CREATE TABLE Sprint (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numero INT NOT NULL,
    fechaInicio DATE NOT NULL,
    fechaFin DATE,
    estado_id INT,
    FOREIGN KEY (estado_id) REFERENCES Estado(id)
);

-- Crear tabla Tarea
CREATE TABLE Tarea (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT NOT NULL,
    fechaInicio DATE NOT NULL,
    tiempoEstimaRealizar INT,
    tiempoEjecucion INT,
    fechaFinEstimada DATE,
    fechaFinReal DATE,
    estado_id INT,
    usuario_id INT,
    prioridad_id INT,
    FOREIGN KEY (estado_id) REFERENCES Estado(id),
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id),
    FOREIGN KEY (prioridad_id) REFERENCES Prioridad(id)
);

-- Crear tabla PermisosXRol
CREATE TABLE PermisosXRol (
    rol_id INT NOT NULL,
    permiso_id INT NOT NULL,
    PRIMARY KEY (rol_id, permiso_id),
    FOREIGN KEY (rol_id) REFERENCES Rol(id) ON DELETE CASCADE,
    FOREIGN KEY (permiso_id) REFERENCES Permiso(id) ON DELETE CASCADE
);

-- Crear tabla AccionesXPermiso
CREATE TABLE AccionesXPermiso (
    permiso_id INT NOT NULL,
    accion_id INT NOT NULL,
    PRIMARY KEY (permiso_id, accion_id),
    FOREIGN KEY (permiso_id) REFERENCES Permiso(id) ON DELETE CASCADE,
    FOREIGN KEY (accion_id) REFERENCES Accion(id) ON DELETE CASCADE
);

-- Crear tabla SprintXProyecto
CREATE TABLE SprintXProyecto (
    proyecto_id INT NOT NULL,
    sprint_id INT NOT NULL,
    PRIMARY KEY (proyecto_id, sprint_id),
    FOREIGN KEY (proyecto_id) REFERENCES Proyecto(id) ON DELETE CASCADE,
    FOREIGN KEY (sprint_id) REFERENCES Sprint(id) ON DELETE CASCADE
);

-- Crear tabla TareaXSprint
CREATE TABLE TareaXSprint (
    sprint_id INT NOT NULL,
    tarea_id INT NOT NULL,
    PRIMARY KEY (sprint_id, tarea_id),
    FOREIGN KEY (sprint_id) REFERENCES Sprint(id) ON DELETE CASCADE,
    FOREIGN KEY (tarea_id) REFERENCES Tarea(id) ON DELETE CASCADE
);
