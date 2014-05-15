package br.unb.cic.poo.imdb.data;

import br.unb.cic.poo.imdb.domain.Autor;
import br.unb.cic.poo.imdb.domain.TrabalhoArtistico;

/**
 * Interface definindo os metodos de acesso a 
 * dados para a classe Autor.
 * 
 * @author rbonifacio
 */
public interface IAutorDAO {

	/**
	 * Salva um autor no banco de dados 
	 * @param autor nao pode ser nulo
	 * @throws DBException caso algum problema ocorra na camada de persistencia
	 */
	public void salvarAutor(Autor autor) throws DBException;
	
	/**
	 * Salva um trabalho artistico no banco de dados. 
	 * @param idAutor identificador do autor 
	 * @param t trabalho artistico 
	 */
	public void salvarTrabalhoArtistico(long idAutor, TrabalhoArtistico trabalhoArtistico) throws AutorInexistente, DBException;
	
	/**
	 * Pesquisa um autor pelo id informado
	 * @param id usado como criterio de pesquisa
	 * @return autor cujo id eh informado como argumento
	 * @throws DBException caso algum problema ocorra na camada de persistencia
	 */
	public Autor pesquisaPorId(long id) throws DBException;
	
	/**
	 * Pesquisa um autor pelo id informado e recupera a 
	 * producao do autor. 
	 * 
	 * @param id identicador do autor
	 * @return autor com toda sua producao artistica 
	 * 
	 * @throws DBException caso algum problema ocorra
	 */
	public Autor carregarProducao(long id) throws DBException;
	
	/**
	 * Remove um autor do banco de dados
	 * @param id identificador do autor a ser removido 
	 * @throws DBException caso algum problema ocorra na camada de persistencia
	 */
	public void deletarAutor(long id) throws DBException;
}