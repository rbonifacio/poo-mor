package br.unb.cic.poo.imdb.data.jdbc;


public class DAOFactory {

	private static DAOFactory instance;
	
	private DAOFactory() { }
	
	public static DAOFactory instance() {
		if(instance == null) {
			instance = new DAOFactory();
		}
		return instance;
	}
	
	public JDBCManager manager() {
		return new SQLite();
	}
	
}
