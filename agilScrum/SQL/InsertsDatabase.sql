-- Insertar registros en la tabla Prioridad
INSERT INTO Prioridad (nombre, codigo) VALUES ('Alta', 'ALTA');
INSERT INTO Prioridad (nombre, codigo) VALUES ('Media', 'MEDIA');
INSERT INTO Prioridad (nombre, codigo) VALUES ('Baja', 'BAJA');

-- Insertar registros en la tabla Estado para Tareas
INSERT INTO Estado (nombre, codigo) VALUES ('Pendiente', 'TAREA');
INSERT INTO Estado (nombre, codigo) VALUES ('En Progreso', 'TAREA');
INSERT INTO Estado (nombre, codigo) VALUES ('Completada', 'TAREA');
INSERT INTO Estado (nombre, codigo) VALUES ('Bloqueada', 'TAREA');

-- Insertar registros en la tabla Estado para Sprints
INSERT INTO Estado (nombre, codigo) VALUES ('Planificado', 'SPRINT');
INSERT INTO Estado (nombre, codigo) VALUES ('En Progreso', 'SPRINT');
INSERT INTO Estado (nombre, codigo) VALUES ('Completado', 'SPRINT');

-- Insertar registros en la tabla Estado para Proyectos
INSERT INTO Estado (nombre, codigo) VALUES ('Iniciado', 'PROYECTO');
INSERT INTO Estado (nombre, codigo) VALUES ('En Progreso', 'PROYECTO');
INSERT INTO Estado (nombre, codigo) VALUES ('Completado', 'PROYECTO');
INSERT INTO Estado (nombre, codigo) VALUES ('Cancelado', 'PROYECTO');