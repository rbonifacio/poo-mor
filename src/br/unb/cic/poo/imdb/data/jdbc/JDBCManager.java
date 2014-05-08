package br.unb.cic.poo.imdb.data.jdbc;

import java.sql.Connection;

import br.unb.cic.poo.imdb.data.DBException;

/**
 * Define um metodo que abstrai a forma como obtemos uma 
 * conexao com o banco de dados usando a tecnologia JDBC. 
 * 
 * @author rbonifacio
 */
public interface JDBCManager {

	/**
	 * @return conexao JDBC com o banco de dados
	 * @throws DBException caso nao seja possivel conectar com o banco de dados
	 */
	public Connection getConnection() throws DBException;
}
