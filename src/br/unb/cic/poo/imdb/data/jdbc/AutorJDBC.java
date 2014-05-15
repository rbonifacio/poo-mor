package br.unb.cic.poo.imdb.data.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.unb.cic.poo.imdb.data.AutorInexistente;
import br.unb.cic.poo.imdb.data.DBException;
import br.unb.cic.poo.imdb.data.IAutorDAO;
import br.unb.cic.poo.imdb.domain.Autor;
import br.unb.cic.poo.imdb.domain.TrabalhoArtistico;

/**
 * Implementacao da interface {@link IAutorDAO}
 * baseada na tecnologia JDBC. 
 * 
 * @author rbonifacio
 */
public class AutorJDBC implements br.unb.cic.poo.imdb.data.IAutorDAO{

	private static final String CONSULTA_PRODUCAO_AUTOR = 
			"SELECT A.ID_AUTOR, A.NOME, A.DESCRICAO, " +
	          "       T.ID_TRABALHO_ARTISTICO, T.TITULO, T.ANO_PRODUCAO, " +
	          "       G.ID_GENERO, G.NOME, G.DESCRICAO " +
	          "FROM TB_AUTOR A, TB_TRABALHO_ARTISTICO T, TB_GENERO G " +
	          "WHERE A.ID_AUTOR = T.FK_AUTOR " +
	          "  AND G.ID_GENERO = T.FK_GENERO " +
	          "  AND A.ID_AUTOR = ?"; 
	@Override
	public void salvarAutor(Autor autor) throws DBException {
		JDBCManager manager = DAOFactory.instance().manager();
	
		try {
			Connection con = manager.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement("INSERT INTO TB_AUTOR(ID_AUTOR, NOME, DESCRICAO) VALUES(?, ?, ?)");
		
			ps.setLong(1, autor.getId());
			ps.setString(2, autor.getNome());
			ps.setString(3, autor.getDescricao());
		
			ps.executeUpdate();
			con.commit();
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new DBException(e);
		}
	}

	@Override
	public Autor pesquisaPorId(long id) throws DBException {
		JDBCManager manager = DAOFactory.instance().manager();
		
		try {
			Connection con = manager.getConnection();
			
			PreparedStatement ps = con.prepareStatement(
					"SELECT ID_AUTOR, NOME, DESCRICAO " +
					"FROM TB_AUTOR " +
					"WHERE ID_AUTOR = ?");
			
			ps.setLong(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) { //esperamos apenas uma linha
				String nome = rs.getString(2);
				String descricao = rs.getString(3);
				con.close();
				return new Autor(id, nome, descricao);
			}
			else {
				con.close();
				return null;
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new DBException(e);
		}
	}

	@Override
	public void deletarAutor(long id) throws DBException {
		JDBCManager manager = DAOFactory.instance().manager();
		Connection con = null;
		try {
			con = manager.getConnection();
			con.setAutoCommit(false);
			
			PreparedStatement ps1 = con.prepareStatement(
					"DELETE FROM TB_TRABALHO_ARTISTICO " +
					"WHERE FK_AUTOR = ?");
			
			PreparedStatement ps2 = con.prepareStatement(
					"DELETE FROM TB_AUTOR WHERE ID_AUTOR = ?");
			
			ps1.setLong(1, id);
			ps1.executeUpdate();
			
			ps2.setLong(1, id);
			ps2.executeUpdate();
			
			con.commit();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
			throw new DBException(e);
		}
	}

	@Override
	public void salvarTrabalhoArtistico(long idAutor, TrabalhoArtistico trabalhoArtistico) throws AutorInexistente, DBException{
		JDBCManager manager = DAOFactory.instance().manager();
		Connection con = null;
		try {
			con = manager.getConnection();
			Autor autor = pesquisaPorId(idAutor);
			if(autor == null) {
				throw new AutorInexistente();
			}
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO TB_TRABALHO_ARTISTICO " +
					"(ID_TRABALHO_ARTISTICO, " +  
					" FK_AUTOR, " +
					" FK_GENERO," +  
				    " TITULO, " + 
					" ANO_PRODUCAO) VALUES(?, ?, ?, ?, ?)");
			
			ps.setLong(1, trabalhoArtistico.getId());
			ps.setLong(2, idAutor);
			ps.setLong(3, 1); // ID do genero 
			ps.setString(4, trabalhoArtistico.getTitulo());
			ps.setInt(5, trabalhoArtistico.getAnoProducao());
			
			ps.executeUpdate();
			con.commit();
			con.close();
			
		}
		catch(Exception e) {
			throw new DBException(e);
		}
	}

	@Override
	public Autor carregarProducao(long id) throws DBException {
		JDBCManager manager = DAOFactory.instance().manager();
		Connection con = null; 
		
		try{
			con = manager.getConnection();
			PreparedStatement ps = con.prepareStatement(CONSULTA_PRODUCAO_AUTOR);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				String nome = rs.getString(2);
				String descricao = rs.getString(3);
				Autor autor = new Autor(id, nome, descricao);
			
				Long idTrabalhoArtistico = rs.getLong(4);
				String titulo = rs.getString(5);
				int anoProducao = rs.getInt(6);
			
				TrabalhoArtistico trabalho = new TrabalhoArtistico(idTrabalhoArtistico, titulo, anoProducao);
				
				autor.adicionaTrabalhoArtistico(trabalho);
				
				while(rs.next()) {
					idTrabalhoArtistico = rs.getLong(4);
					titulo = rs.getString(5);
					anoProducao = rs.getInt(6);
						
					trabalho = new TrabalhoArtistico(idTrabalhoArtistico, titulo, anoProducao);
					
					autor.adicionaTrabalhoArtistico(trabalho);
					
				}
				return autor;
			}
			return null;
		}
		catch(Exception e) {
			throw new DBException(e);
		}
		finally{
			try {
				con.close();
			}
			catch(Exception e) {
				throw new DBException(e);
			}
		}
	}
}
