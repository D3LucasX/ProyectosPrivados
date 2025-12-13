package model;

public class Paginas {
	private String idNoticia;
	private String url;
	private String filtro;
	
	public Paginas(String idNoticia, String url, String filtro) {
		this.idNoticia = idNoticia;
		this.url = url;
		this.filtro = filtro;
	}

	public String getIdNoticia() {
		return idNoticia;
	}

	public void setIdNoticia(String idNoticia) {
		this.idNoticia = idNoticia;
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
