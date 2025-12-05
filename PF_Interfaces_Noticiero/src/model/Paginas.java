package model;

public class Paginas {
	private String url;
	private String filtro;
	
	public Paginas(String url, String filtro) {
		this.url = url;
		this.filtro = filtro;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
	
}
