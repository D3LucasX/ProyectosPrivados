package model;

import java.util.Date;

public class Cambio {
	
	private int idCambio;
	private String motivo;
	private Date fecha;
	private Stand standOriginal;
	private Zona zonaOriginal;
	private Juguete JugueteOriginal;
	private Stand standNuevo;
	private Zona zonaNueva;
	private Juguete jugueteNuevo;
	private Empleado empleado;
	
	public Cambio(int idCambio, String motivo, Date fecha, Stand standOriginal, Zona zonaOriginal,
			Juguete jugueteOriginal, Stand standNuevo, Zona zonaNueva, Juguete jugueteNuevo, Empleado empleado) {
		super();
		this.idCambio = idCambio;
		this.motivo = motivo;
		this.fecha = fecha;
		this.standOriginal = standOriginal;
		this.zonaOriginal = zonaOriginal;
		JugueteOriginal = jugueteOriginal;
		this.standNuevo = standNuevo;
		this.zonaNueva = zonaNueva;
		this.jugueteNuevo = jugueteNuevo;
		this.empleado = empleado;
	}
	public Cambio(String motivo, int standOriginal, int zonaOriginal,
			int jugueteOriginal, int standNuevo, int zonaNueva, int jugueteNuevo, int idEmpleado) {
		
		this.motivo = motivo;
		this.standOriginal = new Stand(standOriginal);
		this.zonaOriginal = new Zona(zonaOriginal);
		JugueteOriginal = new Juguete(jugueteOriginal);
		this.standNuevo = new Stand(standNuevo);
		this.zonaNueva = new Zona(zonaNueva);
		this.jugueteNuevo = new Juguete(jugueteNuevo);
		this.empleado = new Empleado(idEmpleado);
	}

	public int getIdCambio() {
		return idCambio;
	}

	public void setIdCambio(int idCambio) {
		this.idCambio = idCambio;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Stand getStandOriginal() {
		return standOriginal;
	}

	public void setStandOriginal(Stand standOriginal) {
		this.standOriginal = standOriginal;
	}

	public Zona getZonaOriginal() {
		return zonaOriginal;
	}

	public void setZonaOriginal(Zona zonaOriginal) {
		this.zonaOriginal = zonaOriginal;
	}

	public Juguete getJugueteOriginal() {
		return JugueteOriginal;
	}

	public void setJugueteOriginal(Juguete jugueteOriginal) {
		JugueteOriginal = jugueteOriginal;
	}

	public Stand getStandNuevo() {
		return standNuevo;
	}

	public void setStandNuevo(Stand standNuevo) {
		this.standNuevo = standNuevo;
	}

	public Zona getZonaNueva() {
		return zonaNueva;
	}

	public void setZonaNueva(Zona zonaNueva) {
		this.zonaNueva = zonaNueva;
	}

	public Juguete getJugueteNuevo() {
		return jugueteNuevo;
	}

	public void setJugueteNuevo(Juguete jugueteNuevo) {
		this.jugueteNuevo = jugueteNuevo;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
}
