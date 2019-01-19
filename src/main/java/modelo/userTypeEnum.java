package modelo;

public enum userTypeEnum {
	DEVELOPER, //solo visualizar proyectos asignados
	PRODUCT_OWNER, //visualizar su / sus proyectos y anyadir/modificar/eliminar especiicaciones
	SCRUM_MASTER, //visualizar proyectos y crearlos, crear sprint temporizar especificaciones y sprints, asignar especificaciones a sprint
	USER_ADMINISTRATOR //puede crear usuarios
}
