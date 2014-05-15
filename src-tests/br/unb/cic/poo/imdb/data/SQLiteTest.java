package br.unb.cic.poo.imdb.data;

import java.sql.Connection;

import br.unb.cic.poo.imdb.data.jdbc.AutorJDBC;
import br.unb.cic.poo.imdb.data.jdbc.JDBCManager;
import br.unb.cic.poo.imdb.data.jdbc.SQLite;
import br.unb.cic.poo.imdb.domain.Autor;
import br.unb.cic.poo.imdb.domain.TrabalhoArtistico;

import junit.framework.TestCase;

public class SQLiteTest extends TestCase {

	public void setUp() {
		AutorJDBC dao = new AutorJDBC();
		
		try {
			Autor sy = dao.pesquisaPorId(1L);
			
			if(sy != null) {
				dao.deletarAutor(1L);
			}
		}
		catch(Exception e) {
			fail();
		}
	}
	public void testGetConnection() {
		JDBCManager manager = new SQLite();
		
		try {
			Connection c = manager.getConnection();
			assertNotNull(c);
		}
		catch(DBException e) {
			fail();
		}
	}
	
	public void testSalvarAutor() {
		AutorJDBC dao = new AutorJDBC();
		
		try {
			String nome = "Sonic Youth";
			String descricao = "Sonic Youth was an American alternative rock band from New York City, formed in 1981. Their most recent...";
			
			dao.salvarAutor(new Autor(1L, nome, descricao));
			dao.salvarTrabalhoArtistico(1L, new TrabalhoArtistico(1L, "GOO", 1990));
			
			Autor autor = dao.pesquisaPorId(1L);
			
			assertNotNull(autor);
			assertEquals(nome, autor.getNome());
			assertEquals(descricao, autor.getDescricao());
			assertEquals(0, autor.producao());
		}
		catch(Exception e) {
			fail();
		}
	}
	
	public void testIncluirTrabalhoArtistico() {
		AutorJDBC dao = new AutorJDBC();
		try {
			String titulo1 = "Dirty";
			int anoProducao1 = 1988;
			
			String titulo2 = "Goo";
			int anoProducao2 = 1990;
			
			dao.salvarAutor(new Autor(1L, "Sonic Youth", "foo"));
			dao.salvarTrabalhoArtistico(1L, new TrabalhoArtistico(1L, titulo1, anoProducao1));
			dao.salvarTrabalhoArtistico(1L, new TrabalhoArtistico(2L, titulo2, anoProducao2));
			
			Autor autor = dao.carregarProducao(1L);
			
			assertEquals(2, autor.producao());
		}
		catch(Exception e) {
			e.getCause().printStackTrace();
			fail();
		}
	}
}
