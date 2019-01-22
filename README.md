# ScrumPanel

Miembros del proyecto
===========================================
Jaime Peña
Jordi Jacas
Sean Saez

Instrucciones para ejecutar el proyecto
===========================================

1 - Crear una Base de Datos llamada scrumdb y crear un usuario con nombre "jsj" y contraseña "jsj12321" 
    nuevo con todos los permisos para la Base de Datos scrumdb.
    
    Ejecutar las sigientes sentecias sql para insertar datos iniziales a la Base de Datos:
    
    INSERT INTO `usuario`(`contraseña`, `email`, `nombre`, `nombre_usuario`, `rol_usuario`) VALUES  ('P@ssw0rd','admin.usuario@gmail.com','Administrador Usuario','admin.usuario',3)
    INSERT INTO `usuario`(`contraseña`, `email`, `nombre`, `nombre_usuario`, `rol_usuario`) VALUES ('P@ssw0rd','user.1@gmail.com','User 1','user.1',1)

2 - Importar el repositorio https://github.com/JordiJacas/ScrumPanel.

3 - Abrir el documento persistence.xml que se encuentra en "src/main/resources/META-INF"
    modificar la ip.
    "<property name="javax.persistence.jdbc.url" value="jdbc:mysql://<IP>:3306/scrumdb?serverTimezone=UTC" />"
    modificar la ip
    
4 - En la carpeta src/main/java/daoTest ejecutar main.java.


Datos insertados al ejecutar en la Base de Datos
===================================================
Nombre_Usuario: admin.usuario
Contrasenya: P@ssw0rd
