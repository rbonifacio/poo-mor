package br.unb.cic.poo.imdb.domain;

/**
 * Representa um trabalho artistico.
 * 
 * @author rbonifacio
 */
public class TrabalhoArtistico {

	private Long id;
	private String titulo;
	private int anoProducao;
	
	public TrabalhoArtistico(Long id, String titulo, int anoProducao) {
		this.id = id;
		this.titulo = titulo;
		this.anoProducao = anoProducao;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public int getAnoProducao() {
		return anoProducao;
	}
	
	public void setAnoProducao(int anoProducao) {
		this.anoProducao = anoProducao;
	}
}
