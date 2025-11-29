package model;

import java.util.Date;

public class Venta {

	private int idVenta;
	private Date fecaVenta;
	private double montoTotal;
	private Pago tipoPago;
	private Juguete idJuguete;
	private Empleado idEmpleado;
	private Stand idStand;
	private Zona idZonaStand;
	
	public enum Pago{EFECTIVO, TARJETA}

	public Venta(int idVenta, double montoTotal,
			int idStand, int idZonaStand, int idJuguete) {
		super();
		this.idVenta = idVenta;
		this.montoTotal = montoTotal;
		this.idStand = new Stand (idStand);
		this.idZonaStand = new Zona (idZonaStand);
		this.idJuguete = new Juguete(idJuguete);
	}
	
	public Venta(double montoTotal, Pago tipoPago, int idJuguete, int idEmpleadoI,
			int idStand, int idZonaStand) {
		super();
		this.montoTotal = montoTotal;
		this.tipoPago = tipoPago;
		this.idJuguete = new Juguete(idJuguete);
		this.idEmpleado = new Empleado(idEmpleadoI);
		this.idStand = new Stand (idStand);
		this.idZonaStand = new Zona (idZonaStand);
	}
	
	public Venta(Date fecaVenta, double montoTotal, Pago tipoPago, Juguete idJuguete, Empleado idEmpleado,
			Stand idStand, Zona idZonaStand) {
		this.fecaVenta = fecaVenta;
		this.montoTotal = montoTotal;
		this.tipoPago = tipoPago;
		this.idJuguete = idJuguete;
		this.idEmpleado = idEmpleado;
		this.idStand = idStand;
		this.idZonaStand = idZonaStand;
	}

	public int getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	public Date getFecaVenta() {
		return fecaVenta;
	}

	public void setFecaVenta(Date fecaVenta) {
		this.fecaVenta = fecaVenta;
	}

	public double getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(double montoTotal) {
		this.montoTotal = montoTotal;
	}

	public Pago getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(Pago tipoPago) {
		this.tipoPago = tipoPago;
	}

	public Juguete getIdJuguete() {
		return idJuguete;
	}

	public void setIdJuguete(Juguete idJuguete) {
		this.idJuguete = idJuguete;
	}

	public Empleado getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Empleado idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public Stand getIdStand() {
		return idStand;
	}

	public void setIdStand(Stand idStand) {
		this.idStand = idStand;
	}

	public Zona getIdZonaStand() {
		return idZonaStand;
	}

	public void setIdZonaStand(Zona idZonaStand) {
		this.idZonaStand = idZonaStand;
	};
	
	
}
