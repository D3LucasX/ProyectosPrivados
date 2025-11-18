package PFJugueteria;

public class Stand {

	private int idStand;
	private String nombre;
	private String descripcion;
	private Zona zona;
	public Stand(int idStand, String nombre, String descripcion, Zona zona) {
		super();
		this.idStand = idStand;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.zona = zona;
	}
	
	
	public int getIdStand() {
		return idStand;
	}
	public void setIdStand(int idStand) {
		this.idStand = idStand;
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
	public Zona getZona() {
		return zona;
	}
	public void setZona(Zona zona) {
		this.zona = zona;
	}
	
	
}
