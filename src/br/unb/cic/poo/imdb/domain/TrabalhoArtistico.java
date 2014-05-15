package br.unb.cic.poo.imdb.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Representa um trabalho artistico.
 * 
 * @author rbonifacio
 */
@Table(name="TB_TRABALHO_ARTISTICO")
@Entity
public class TrabalhoArtistico {

	@Id
	@Column(name="ID_TRABALHO_ARTISTICO")
	private Long id;
	
	@Column(name="TITULO")
	private String titulo;
	
	@Column(name="ANO_PRODUCAO")
	private int anoProducao;
	
	@ManyToOne
	@JoinColumn(name="FK_AUTOR")
	private Autor autor;
	
	@ManyToOne(targetEntity=Genero.class)
	@JoinColumn(name="FK_GENERO")
	private Genero genero;
	
	/**
	 * Construtor default de acordo com a 
	 * tecnologia JPA.
	 */
	public TrabalhoArtistico() { }
	
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

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
	
}
