package model;

import java.sql.Date;

public class Juguete {

	private int idJuguete;
	private String nombre;
	private String descripcion;
	private double precio;
	private int stock;
	private int activo;
	private Date fecha_baja;
	public Juguete(String nombre, String descripcion, double precio, int stock, int activo, Date fecha_baja) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.activo = activo;
		this.fecha_baja = fecha_baja;
	}
	public Juguete(int idJuguete, String nombre, String descripcion, double precio, int stock, int activo, Date fecha_baja) {
		this.idJuguete = idJuguete;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.activo = activo;
		this.fecha_baja = fecha_baja;
	}
	public Juguete(int idJuguete) {
		this.idJuguete = idJuguete;
	}
	public int getIdJuguete() {
		return idJuguete;
	}
	public void setIdJuguete(int idJuguete) {
		this.idJuguete = idJuguete;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
}
