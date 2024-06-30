-- Insertar registros en la tabla Prioridad
INSERT INTO Prioridad (nombre, codigo) VALUES ('Alta', 'ALTA');
INSERT INTO Prioridad (nombre, codigo) VALUES ('Media', 'MEDIA');
INSERT INTO Prioridad (nombre, codigo) VALUES ('Baja', 'BAJA');
----------------------------------------------------------------

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
---------------------------------------------------------------------

-- Insertar registros en la tabla Rol
INSERT INTO Rol (nombre, codigo) VALUES ('Scrum Master', 'MASTER');
INSERT INTO Rol (nombre, codigo) VALUES ('Desarrollador', 'DESARROLLADOR');
INSERT INTO Rol (nombre, codigo) VALUES ('Product Owner', 'OWNER');
---------------------------------------------------------------------------------

-- Insertar registros en la tabla Accion
INSERT INTO Accion (nombre, codigo) VALUES ('Registrar proyecto', 'REGISTRAR_PROYECTO');
INSERT INTO Accion (nombre, codigo) VALUES ('Listar proyecto', 'LISTAR_PROYECTO');
INSERT INTO Accion (nombre, codigo) VALUES ('Registrar sprint', 'REGISTRAR_SPRINT');
INSERT INTO Accion (nombre, codigo) VALUES ('Listar sprint', 'LISTAR_SPRINT');
INSERT INTO Accion (nombre, codigo) VALUES ('Asignar sprint al proyecto', 'ASIGNAR_SPRINT_PROYECTO');
INSERT INTO Accion (nombre, codigo) VALUES ('Registrar tareas', 'REGISTRAR_TAREA');
INSERT INTO Accion (nombre, codigo) VALUES ('Asignar tareas', 'ASIGNAR_TAREA');
INSERT INTO Accion (nombre, codigo) VALUES ('Asignar desarrollador', 'ASIGNAR_DESARROLLADOR');
INSERT INTO Accion (nombre, codigo) VALUES ('Generar reporte de desvio', 'REPORTE_DESVIO');

INSERT INTO Accion (nombre, codigo) VALUES ('Consultar tareas asignadas', 'CONSULTAR_TAREAS_ASIGNADAS');
INSERT INTO Accion (nombre, codigo) VALUES ('Asignar estimacion', 'ASIGNAR_ESTIMACION');
INSERT INTO Accion (nombre, codigo) VALUES ('Asignar estado a tarea', 'ASIGNAR_ESTADO_TAREA');

INSERT INTO Accion (nombre, codigo) VALUES ('Consultar proyecto', 'CONSULTAR_PROYECTO');
---------------------------------------------------------------------------------

-- Insertar registros en la tabla Permiso
INSERT INTO Permiso (nombre, codigo) VALUES ('Scrum Master', 'PERMISO_MASTER');
INSERT INTO Permiso (nombre, codigo) VALUES ('Desarrollador', 'PERMISO_DESARROLLADOR');
INSERT INTO Permiso (nombre, codigo) VALUES ('Product Owner', 'PERMISO_OWNER');
---------------------------------------------------------------------------------

------------ Insertar registros en la tabla PermisoXRol ------------------------
-- Obtener los ID de los roles
SELECT id INTO @id_master FROM Rol WHERE codigo = 'MASTER';
SELECT id INTO @id_desarrollador FROM Rol WHERE codigo = 'DESARROLLADOR';
SELECT id INTO @id_owner FROM Rol WHERE codigo = 'OWNER';

-- Obtener los ID de los permisos
SELECT id INTO @permiso_master FROM Permiso WHERE codigo = 'PERMISO_MASTER';
SELECT id INTO @permiso_desarrollador FROM Permiso WHERE codigo = 'PERMISO_DESARROLLADOR';
SELECT id INTO @permiso_owner FROM Permiso WHERE codigo = 'PERMISO_OWNER';

-- Inserción en la tabla permisosxrol
INSERT INTO permisosxrol (rol_id, permiso_id) VALUES (@id_master, @permiso_master);
INSERT INTO permisosxrol (rol_id, permiso_id) VALUES (@id_desarrollador, @permiso_desarrollador);
INSERT INTO permisosxrol (rol_id, permiso_id) VALUES (@id_owner, @permiso_owner);
---------------------------------------------------------------------------------

------------ Insertar registros en la tabla AccionesXPermiso ------------------------
-- Obtener ID del permiso MASTER
SELECT id INTO @permiso_master FROM Permiso WHERE codigo = 'PERMISO_MASTER';

-- Obtener IDs de las acciones del rol MASTER
SELECT id INTO @accion_registrar_proyecto FROM Accion WHERE codigo = 'REGISTRAR_PROYECTO';
SELECT id INTO @accion_listar_proyecto FROM Accion WHERE codigo = 'LISTAR_PROYECTO';
SELECT id INTO @accion_registrar_sprint FROM Accion WHERE codigo = 'REGISTRAR_SPRINT';
SELECT id INTO @accion_listar_sprint FROM Accion WHERE codigo = 'LISTAR_SPRINT';
SELECT id INTO @accion_asignar_sprint_proyecto FROM Accion WHERE codigo = 'ASIGNAR_SPRINT_PROYECTO';
SELECT id INTO @accion_registrar_tarea FROM Accion WHERE codigo = 'REGISTRAR_TAREA';
SELECT id INTO @accion_asignar_tarea FROM Accion WHERE codigo = 'ASIGNAR_TAREA';
SELECT id INTO @accion_asignar_desarrollador FROM Accion WHERE codigo = 'ASIGNAR_DESARROLLADOR';
SELECT id INTO @accion_generar_reporte_desvio FROM Accion WHERE codigo = 'REPORTE_DESVIO';

-- Obtener ID del permiso DESARROLLADOR
SELECT id INTO @permiso_desarrollador FROM Permiso WHERE codigo = 'PERMISO_DESARROLLADOR';

-- Obtener IDs de las acciones del rol DESARROLLADOR
SELECT id INTO @accion_consultar_tareas_asignadas FROM Accion WHERE codigo = 'CONSULTAR_TAREAS_ASIGNADAS';
SELECT id INTO @accion_asignar_estimacion FROM Accion WHERE codigo = 'ASIGNAR_ESTIMACION';
SELECT id INTO @accion_asignar_estado_tarea FROM Accion WHERE codigo = 'ASIGNAR_ESTADO_TAREA';

-- Obtener ID del permiso OWNER
SELECT id INTO @permiso_owner FROM Permiso WHERE codigo = 'PERMISO_OWNER';

-- Obtener IDs de las acciones del rol OWNER
SELECT id INTO @accion_consultar_proyecto FROM Accion WHERE codigo = 'CONSULTAR_PROYECTO';

-- Inserción de acciones para el rol MASTER
INSERT INTO accionesxpermiso (permiso_id, accion_id) VALUES (@permiso_master, @accion_registrar_proyecto);
INSERT INTO accionesxpermiso (permiso_id, accion_id) VALUES (@permiso_master, @accion_listar_proyecto);
INSERT INTO accionesxpermiso (permiso_id, accion_id) VALUES (@permiso_master, @accion_registrar_sprint);
INSERT INTO accionesxpermiso (permiso_id, accion_id) VALUES (@permiso_master, @accion_listar_sprint);
INSERT INTO accionesxpermiso (permiso_id, accion_id) VALUES (@permiso_master, @accion_asignar_sprint_proyecto);
INSERT INTO accionesxpermiso (permiso_id, accion_id) VALUES (@permiso_master, @accion_registrar_tarea);
INSERT INTO accionesxpermiso (permiso_id, accion_id) VALUES (@permiso_master, @accion_asignar_tarea);
INSERT INTO accionesxpermiso (permiso_id, accion_id) VALUES (@permiso_master, @accion_asignar_desarrollador);
INSERT INTO accionesxpermiso (permiso_id, accion_id) VALUES (@permiso_master, @accion_generar_reporte_desvio);

-- Inserción de acciones para el rol DESARROLLADOR
INSERT INTO accionesxpermiso (permiso_id, accion_id) VALUES (@permiso_desarrollador, @accion_consultar_tareas_asignadas);
INSERT INTO accionesxpermiso (permiso_id, accion_id) VALUES (@permiso_desarrollador, @accion_asignar_estimacion);
INSERT INTO accionesxpermiso (permiso_id, accion_id) VALUES (@permiso_desarrollador, @accion_asignar_estado_tarea);

-- Inserción de acciones para el rol OWNER
INSERT INTO accionesxpermiso (permiso_id, accion_id) VALUES (@permiso_owner, @accion_consultar_proyecto);
---------------------------------------------------------------------------------

------------ Insertar registros en la tabla Usuario ------------------------
-- Obtener IDs de los roles
SELECT id INTO @rol_master FROM Rol WHERE codigo = 'MASTER';
SELECT id INTO @rol_desarrollador FROM Rol WHERE codigo = 'DESARROLLADOR';
SELECT id INTO @rol_owner FROM Rol WHERE codigo = 'OWNER';

-- Inserción de usuarios
INSERT INTO usuario (nombre, apellido, mail, usuario, contraseña, rol_id) 
VALUES ('NombreMaster', 'ApellidoMaster', 'master@example.com', 'master', 'master', @rol_master);

INSERT INTO usuario (nombre, apellido, mail, usuario, contraseña, rol_id) 
VALUES ('NombreDesarrollador', 'ApellidoDesarrollador', 'desarrollador@example.com', 'desarrollador', 'desarrollador', @rol_desarrollador);

INSERT INTO usuario (nombre, apellido, mail, usuario, contraseña, rol_id) 
VALUES ('NombreOwner', 'ApellidoOwner', 'owner@example.com', 'owner', 'owner', @rol_owner);
---------------------------------------------------------------------------------