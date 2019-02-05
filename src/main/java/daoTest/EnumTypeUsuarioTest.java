package daoTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EnumType;

import config.ConnnectDBDao;
import daoImpl.EspecificacionDAOImpl;
import daoImpl.ProyectoDAOImpl;
import daoImpl.UsuarioDAOImpl;
import enumClass.userTypeEnum;
import iDao.IProyecto;
import iDao.IUsuario;
import iDao.iEspecificacion;
import modelo.Especificacion;
import modelo.Proyecto;
import modelo.Usuario;
import modelo.UsuarioConectado;

public class EnumTypeUsuarioTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println(userTypeEnum.USER_ADMINISTRATOR.ordinal());
		Connection con;
		
		/*try {
			con = DriverManager.getConnection("jdbc:sqlite:./data2.sqlite");
        	Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * from Usuario where rol_usuario = 2;");
			while (rs.next()) {
                //usuarios.add(new Usuario(rs.getString("nombre_usuario"), rs.getString("nombre"), rs.getString("password"), rs.getString("email"), userTypeEnum.valueOf(rs.getString("rol_usuario"))));
				System.out.println(rs.getString("nombre_usuario") + " + " + rs.getInt("rol_usuario"));
				if(userTypeEnum.USER_ADMINISTRATOR.ordinal() == Integer.parseInt(rs.getString("rol_usuario")) ) {
					//System.out.println(userTypeEnum.SCRUM_MASTER);
				}
			}
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		
		
		
		
		
		IProyecto pro = new ProyectoDAOImpl();
		iEspecificacion i = new EspecificacionDAOImpl();
		
		List <Proyecto> p = new ArrayList<Proyecto>();
		p.add(new Proyecto("String nombre_proyecto", "String descripcion",null, null,null));
		
		
		IUsuario u = new UsuarioDAOImpl();
		//u.crearUsuario(new Usuario("ombre_usuario", "String nombre", "String password", "String email",
		//userTypeEnum.DEVELOPER, p));
		
		
		Usuario user = u.getUsuarioByNombreUsuario("ombre_usuario");
		System.out.println(user.getGrupo_proyecto_id().get(0).getNombre_proyecto());
		
		List<Especificacion> list = new ArrayList<Especificacion>();
		list.add(new Especificacion("String descripcion",null));
		
		System.out.println("d");
		/*Proyecto proyecto = pro.getProyectoByName("String nombre_proyecto");
		
		System.out.println(i.getAllEspecifiacionByProyecto(proyecto).get(0).getDescripcion());*/
		
		
		

		
		//pro.crearProyecto(new Proyecto("String nombre_proyecto", "String descripcion",null, null, list));
		
		
		
		
		
		
		
		
		
		
		
		
			
		
		//System.out.println(es.getAllEspecifiacionByProyecto(proyecto));
	}

}
