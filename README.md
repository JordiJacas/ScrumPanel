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

2 - Importar el repositorio https://github.com/JordiJacas/ScrumPanel.

3 - Abrir el documento persistence.xml que se encuentra en "src/main/resources/META-INF"
    En la propiedad 
    "<property name="javax.persistence.jdbc.url" value="jdbc:mysql://<IP>:3306/scrumdb?serverTimezone=UTC" />"
    modificar la ip
    
4 - En la carpeta src/main/java/daoTest ejecutar main.java.


Datos insertados al ejecutar en la Base de Datos
===================================================
Nombre_Usuario: admin.usuario
Contrasenya: P@ssw0rd
