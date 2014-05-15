package br.unb.cic.poo.imdb.data.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.Hibernate;

import br.unb.cic.poo.imdb.data.AutorInexistente;
import br.unb.cic.poo.imdb.data.DBException;
import br.unb.cic.poo.imdb.data.IAutorDAO;
import br.unb.cic.poo.imdb.domain.Autor;
import br.unb.cic.poo.imdb.domain.Genero;
import br.unb.cic.poo.imdb.domain.TrabalhoArtistico;

public class AutorJPA implements IAutorDAO {

	private String CONSULTA_PRODUCAO = 
			"from TrabalhoArtistico t " +
			"where t.autor.id = :idAutor";
	
	private EntityManager em;
	
	public AutorJPA() {
		
	}
	@Override
	public void salvarAutor(Autor autor) throws DBException {
		JPAManager manager = SQLite.instance();
		em = manager.getEntityManager();
		try {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(autor);
			em.flush();
			tx.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new DBException(e);
		}
		finally {
			em.close();
		}
	}

	@Override
	public Autor pesquisaPorId(long id) throws DBException {
		JPAManager manager = SQLite.instance();
		em = manager.getEntityManager();
		try {
			return em.find(Autor.class, id);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new DBException(e);
		}
		finally {
			em.close();
		}
	}

	@Override
	public void deletarAutor(long id) throws DBException {
		JPAManager manager = SQLite.instance();
		em = manager.getEntityManager();
		try {
			Autor autor = em.find(Autor.class, id);
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.remove(autor);
			em.flush();
			tx.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new DBException(e);
		}
		finally {
			em.close();
		}
	}
	@Override
	public void salvarTrabalhoArtistico(long idAutor, TrabalhoArtistico trabalhoArtistico) throws AutorInexistente, DBException {
		JPAManager manager = SQLite.instance();
		em = manager.getEntityManager();
		try {
			Autor autor = em.find(Autor.class, idAutor);
			Genero genero = em.find(Genero.class, 1L);
			
			if(autor == null) {
				throw new AutorInexistente();
			}
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			autor.adicionaTrabalhoArtistico(trabalhoArtistico);
			trabalhoArtistico.setAutor(autor);
			trabalhoArtistico.setGenero(genero);
			em.persist(trabalhoArtistico);
			em.merge(autor);
			em.flush();
			tx.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new DBException(e);
		}
	}
	
	@Override
	public Autor carregarProducao(long idAutor) throws DBException {
		JPAManager manager = SQLite.instance();
		em = manager.getEntityManager();
		try { 
			Autor autor = em.find(Autor.class, idAutor);
			Hibernate.initialize(autor.getProducao());
			return autor;
			
//			Autor autor = em.find(Autor.class, idAutor);
//			List<TrabalhoArtistico> producao = em.createQuery(CONSULTA_PRODUCAO)
//					.setParameter("idAutor", idAutor).getResultList();
//			autor.setProducao(producao);
//			return autor;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new DBException(e);
		}
		finally {
			em.close();
		}
	}
}
