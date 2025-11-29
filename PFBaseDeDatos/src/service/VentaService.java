package service;

import java.sql.SQLException;
import java.util.Scanner;

import DAO.JugueteDAO;
import DAO.StockDAO;
import DAO.VentasDAO;
import INPUT.Input;
import model.Cambio;
import model.Juguete;
import model.Stock;
import model.Venta;

public class VentaService {
	private VentasDAO dao;
	private StockDAO daoS;
	private JugueteDAO daoJ;
	private StockService serviceST;
	
	public VentaService() {
		this.dao = new VentasDAO();
		this.daoS = new StockDAO();
		this.daoJ = new JugueteDAO();
		this.serviceST = new StockService();
	}
	
	public boolean realizarVenta(Venta venta) throws SQLException {
		int filas = dao.insertarVenta(venta);
		if (filas > 0) {
			System.out.println("Venta realizada con éxito.");
			return true;
		}else {
			System.err.println("Algo salió mal con la venta.");
		}
		return false;
	}
	
	public Venta obtenerVentaParaCambio(Scanner entrada) throws SQLException {
		dao.listarVentas();
		int idVenta = Input.pedirInt(entrada, "Introduce el id de la venta sobre la que quieres hacer el cambio:");
		Venta ventaCambio = dao.seleccionarVentaPorID(idVenta);
		return ventaCambio;
	}
	
	public boolean realizarCambio(Juguete jugueteDevuelto, Juguete jugueteCambio, Scanner entrada, Cambio cambioNuevo) throws SQLException{
		
		
		boolean devuelto = serviceST.modificarStock(jugueteDevuelto, "sumar");
		
		if(devuelto) {
			System.out.println("Se modifica el stock del producto que se devuelve.");
			boolean cambio = serviceST.modificarStock(jugueteCambio, "restar");
			if (cambio) {
				int filas = dao.insertarCambio(cambioNuevo);
				if(filas > 0) {
					System.out.println("Stock del producto que se lleva de cambio modificado");
					return true;
				}else {
					devuelto = serviceST.modificarStock(jugueteDevuelto, "restar");
					System.err.println("Se cancela la modificacion de stock del juguete que se devolvía.");
					return false;
				}
			}else {
				devuelto = serviceST.modificarStock(jugueteDevuelto, "restar");
				System.err.println("Se cancela la modificacion de stock del juguete que se devolvía.");
				return false;
			}
		}else {
			System.err.println("No se ha podido devolver el stock del juguete devuelto");
			return false;
		}
		
		
	}
}
