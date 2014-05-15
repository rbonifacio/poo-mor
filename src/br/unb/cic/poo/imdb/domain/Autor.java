package br.unb.cic.poo.imdb.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name="TB_AUTOR")
@Entity
public class Autor {
	@Id
	@Column(name="ID_AUTOR")
	private Long id;
	
	@Column(name="NOME")
	private String nome;

	@Column(name="DESCRICAO")
	private String descricao;
	
	
	@OneToMany(mappedBy="autor", cascade={CascadeType.ALL})
	private List<TrabalhoArtistico> producao;
	
	public Autor() {
		producao = new ArrayList<TrabalhoArtistico>();
	}
	
	public Autor(long id, String nome, String descricao) {
		this();
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
	
	public void adicionaTrabalhoArtistico(TrabalhoArtistico t) {
		producao.add(t);
	}	
	
	public int producao() {
		return producao.size();
	}

	public List<TrabalhoArtistico> getProducao() {
		return producao;
	}

	public void setProducao(List<TrabalhoArtistico> producao) {
		this.producao = producao;
	}
	
	
}
