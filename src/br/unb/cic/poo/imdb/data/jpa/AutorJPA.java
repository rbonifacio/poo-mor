package br.unb.cic.poo.imdb.data.jpa;

import javax.persistence.EntityManager;

import br.unb.cic.poo.imdb.data.DBException;
import br.unb.cic.poo.imdb.data.IAutorDAO;
import br.unb.cic.poo.imdb.domain.Autor;

public class AutorJPA implements IAutorDAO {

	private EntityManager em;
	
	public AutorJPA() {
		JPAManager manager = SQLite.instance();
		em = manager.getEntityManager();
	}
	@Override
	public void salvarAutor(Autor autor) throws DBException {
		try {
			//em.getTransaction().begin();
			em.persist(autor);
			//em.flush();
			//em.getTransaction().commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new DBException(e);
		}
	}

	@Override
	public Autor pesquisaPorId(long id) throws DBException {
		try {
			return em.find(Autor.class, id);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new DBException(e);
		}
	}

	@Override
	public void deletarAutor(long id) throws DBException {
		try {
			Autor autor = pesquisaPorId(id);
			//em.getTransaction().begin();
			em.remove(autor);
			//em.flush();
			//em.getTransaction().commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new DBException(e);
		}
	}
}
