package DAO;

import java.util.ArrayList;

import model.Stand;
import model.Zona;

public class StandDAO {
	// Crear lista de stands de ejemplo
	private ArrayList<Stand> crearStandsIniciales(ArrayList<Zona> zonas) {
	    ArrayList<Stand> lista = new ArrayList<>();

	    lista.add(new Stand(1, "Stand de Pelotas", "Variedad de pelotas para todas las edades", zonas.get(0))); // Zona Norte
	    lista.add(new Stand(2, "Stand de Muñecas", "Muñecas y accesorios", zonas.get(0))); 
	    lista.add(new Stand(3, "Stand de Construcción", "Lego y bloques de construcción", zonas.get(1))); // Zona Sur
	    lista.add(new Stand(4, "Stand de Juegos de Mesa", "Rompecabezas, ajedrez y más", zonas.get(2))); // Zona Central
	    lista.add(new Stand(5, "Stand de Peluches", "Peluche y juguetes suaves", zonas.get(2)));
	    lista.add(new Stand(6, "Stand de Coches y Trenes", "Coches a control remoto y trenes de madera", zonas.get(1)));
	    
	    return lista;
	}
}
