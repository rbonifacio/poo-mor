package br.unb.cic.poo.imdb.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.unb.cic.poo.imdb.data.jpa.AutorJPA;
import br.unb.cic.poo.imdb.domain.Autor;

import junit.framework.TestCase;

public class JPATest extends TestCase {
	
	public void setUp() {
		IAutorDAO dao = new AutorJPA();
		
		try {
			Autor autor = dao.pesquisaPorId(12345L);
			if(autor != null) {
				dao.deletarAutor(autor.getId());
			}
			
			autor = dao.pesquisaPorId(1L);
			
			if(autor != null) {
				dao.deletarAutor(1L);
			}
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void testConnection() {
		try 
		{
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("sample");
			assertNotNull(emf);
			
			EntityManager em = emf.createEntityManager();
			assertNotNull(em);
		}catch(Throwable t) {
			t.printStackTrace();
			fail();
		}
	}
	
	public void testSalvarAutor() {
		IAutorDAO dao = new AutorJPA();
		
		try {
			dao.salvarAutor(new Autor(1L, "Sonic Youth", "Sonic Youth was an American alternative rock band from New York City, formed in 1981. Their most recent..."));
			
			Autor sy = dao.pesquisaPorId(1L);
			
			assertNotNull(sy);
			assertEquals("Sonic Youth", sy.getNome());
		}
		catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
