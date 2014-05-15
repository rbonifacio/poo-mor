package br.unb.cic.poo.imdb.data;

import java.util.List;

import junit.framework.TestCase;
import br.unb.cic.poo.imdb.data.jpa.AutorJPA;
import br.unb.cic.poo.imdb.domain.Autor;
import br.unb.cic.poo.imdb.domain.TrabalhoArtistico;

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
			e.getCause().printStackTrace();
			e.printStackTrace();
		}
		
	}
//	public void testConnection() {
//		try 
//		{
//			EntityManagerFactory emf = Persistence.createEntityManagerFactory("sample");
//			assertNotNull(emf);
//			
//			EntityManager em = emf.createEntityManager();
//			assertNotNull(em);
//		}catch(Throwable t) {
//			t.printStackTrace();
//			fail();
//		}
//	}
	
	public void testSalvarAutor() {
		IAutorDAO dao = new AutorJPA();
		
		try {
			String descricao = "(JPA)Sonic Youth was an American alternative rock band from New York City, formed in 1981. Their most recent...";
			dao.salvarAutor(new Autor(1L, "Sonic Youth", descricao));
			
			Autor sy = dao.carregarProducao(1L);
			
			assertNotNull(sy);
			assertEquals("Sonic Youth", sy.getNome());
			
			assertEquals(descricao, sy.getDescricao());
			
			List<TrabalhoArtistico> producao = sy.getProducao();
			
			assertEquals(0, producao.size());
		}
		catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	public void testSalvarProducao() {
IAutorDAO dao = new AutorJPA();
		
		try {
			String nome = "Sonic Youth";
			String descricao = "(JPA)Sonic Youth was an American alternative rock band from New York City, formed in 1981. Their most recent...";
			dao.salvarAutor(new Autor(1L, nome, descricao));
			
			dao.salvarTrabalhoArtistico(1L, new TrabalhoArtistico(2L, "Dirty", 1988));
			dao.salvarTrabalhoArtistico(1L, new TrabalhoArtistico(1L, "Goo", 1992));
			
			Autor sy = dao.carregarProducao(1L);
			
			assertNotNull(sy);
			assertEquals(nome, sy.getNome());
			
			assertEquals(descricao, sy.getDescricao());
			
			List<TrabalhoArtistico> producao = sy.getProducao();
			
			assertEquals(2, producao.size());
		}
		catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
