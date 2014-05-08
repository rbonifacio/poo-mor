package br.unb.cic.poo.imdb.data;

/**
 * Classe usada para lancar excecoes relacionadas 
 * ao acesso a dados. 
 * 
 * @author rbonifacio
 */
public class DBException extends Exception {

	private static final long serialVersionUID = 1L;

	public DBException(Exception e) {
		super(e);
	}
}
