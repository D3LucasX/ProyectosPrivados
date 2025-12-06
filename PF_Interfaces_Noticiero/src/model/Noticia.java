package model;

public class Noticia {
	private String idNoticia;
	private String titulo;
	private String url;
	private String filtro;
	
	public Noticia(String idNoticia, String titulo, String url, String filtro) {
		this.idNoticia = idNoticia;
		this.titulo = titulo;
		this.url = url;
		this.filtro = filtro;
	}
	
	
	public String getIdNoticia() {
		return idNoticia;
	}


	public void setIdNoticia(String idNoticia) {
		this.idNoticia = idNoticia;
	}


	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
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
