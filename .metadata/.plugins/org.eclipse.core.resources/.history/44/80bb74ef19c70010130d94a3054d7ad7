package main;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Juguete;
import service.JugueteService;

public class launcher {

	public static void main(String[] args) {
		JugueteService service = new JugueteService();
		
		try {
            service.inicializarSeedDataJug(); // inserta 10 juguetes si la BD está vacía
            ArrayList<Juguete> todos = service.obtenerTodos();
            todos.forEach(j -> System.out.println(j.getNombre()));
		} catch (SQLException e) {
	        System.err.println("Error al inicializar o listar juguetes: " + e.getMessage());
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
