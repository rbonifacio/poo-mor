package br.unb.cic.poo.imdb.data.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

import br.unb.cic.poo.imdb.data.DBException;

public class SQLite implements JDBCManager {

	private static final String CLASS = "org.sqlite.JDBC";
	private static final String URL = "jdbc:sqlite:data/imdb.db";
	
	@Override
	public Connection getConnection() throws DBException {
		try {
			Class.forName(CLASS);
			return DriverManager.getConnection(URL);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new DBException(e);
		}
	}

}
