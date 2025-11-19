package model;

public class Stock {

	private Stand StockEnStand;
	private Zona zonaStand;
	private Juguete jugueteEnStock;
	private int cantidad;
	public Stock(Stand stockEnStand, Zona zonaStand, Juguete jugueteEnStock, int cantidad) {
		super();
		StockEnStand = stockEnStand;
		this.zonaStand = zonaStand;
		this.jugueteEnStock = jugueteEnStock;
		this.cantidad = cantidad;
	}
	
	
	public Stand getStockEnStand() {
		return StockEnStand;
	}
	public void setStockEnStand(Stand stockEnStand) {
		StockEnStand = stockEnStand;
	}
	public Zona getZonaStand() {
		return zonaStand;
	}
	public void setZonaStand(Zona zonaStand) {
		this.zonaStand = zonaStand;
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
