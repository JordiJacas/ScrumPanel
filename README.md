# ScrumPanel

Miembros del proyecto
===========================================
Jaime Peña
Jordi Jacas
Sean Saez

Instrucciones para ejecutar el proyecto
===========================================

1 - Crear una Base de Datos llamada test y crear un usuario con nombre "jsj" y contraseña "jsj12321" 
    nuevo con todos los permisos para la Base de Datos scrumdb.

2 - Importar el repositorio https://github.com/JordiJacas/ScrumPanel.

3 - Abrir el documento persistence.xml que se encuentra en "src/main/resources/META-INF"
    modificar la ip.
    <property name="javax.persistence.jdbc.url" value="jdbc:mysql://<IP>:3306/scrumdb?serverTimezone=UTC" />
    modificar la ip
    
4 - Ejecutar el fichero src/main/java/conf/insertarDatos.java para introducir los datos de prueba a la BBDD remota.
    En la carpeta src/main/java/daoTest ejecutar main.java.


Usuarios insertados al ejecutar en la Base de Datos
===================================================
Nombre_Usuario: uadmin
Contraseña: 123
Rol: Administrador Usuario

Nombre_Usuario: udev
Contraseña: 123
Rol: Developer

Nombre_Usuario: udev2
Contraseña: 123
Rol: Developer

Nombre_Usuario: uowner
Contraseña: 123
Rol: Product Owner

Nombre_Usuario: uscrum
Contraseña: 123
Rol: Scrum Master
