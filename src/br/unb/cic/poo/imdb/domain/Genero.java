package br.unb.cic.poo.imdb.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="TB_GENERO")
@Entity
public class Genero {
	@Id
	@Column(name="ID_GENERO")
	private Long id;
	
	@Column(name="NOME")
	private String nome;
	
	@Column(name="DESCRICAO")
	private String descricao;
	
	/**
	 * Construtor default, necessario para o 
	 * uso da tecnologia JPA. 
	 */
	public Genero() { }
	
	public Genero(Long id, String nome, String descricao) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
