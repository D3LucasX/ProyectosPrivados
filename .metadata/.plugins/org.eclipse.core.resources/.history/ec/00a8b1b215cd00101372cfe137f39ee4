package model;

public class Stock {

	private Stand idStand;
	private Zona idZona_enStand;
	private Juguete jugueteEnStock;
	private int cantidad;
	
	public Stock(Stand idStand, Zona idZona_enStand, Juguete jugueteEnStock, int cantidad) {
		this.idStand = idStand;
		this.idZona_enStand = idZona_enStand;
		this.jugueteEnStock = jugueteEnStock;
		this.cantidad = cantidad;
	}
	
	public Stock(int idStand, int idZona_enStand, Juguete jugueteEnStock, int cantidad) {
		this.idStand = new Stand(idStand);
		this.idZona_enStand = new Stand(idStand).getZona();
		this.jugueteEnStock = jugueteEnStock;
		this.cantidad = cantidad;
	}
	
	public Stand getIdStand() {
		return idStand;
	}
	public void setIdStand(Stand stockEnStand) {
		idStand = stockEnStand;
	}
	public Zona getIdZona_enStand() {
		return idZona_enStand;
	}
	public void setIdZona_enStand(Zona zonaStand) {
		this.idZona_enStand = zonaStand;
	}
	public Juguete getJugueteEnStock() {
		return jugueteEnStock;
	}
	public void setJugueteEnStock(Juguete jugueteEnStock) {
		this.jugueteEnStock = jugueteEnStock;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
