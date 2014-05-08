package br.unb.cic.poo.imdb.data.jpa;

import javax.persistence.EntityManager;

/**
 * Define um metodo que abstrai o processo para obter 
 * um entity manager, na tecnologia JPA. 
 * 
 * @author rbonifacio
 */
public interface JPAManager {

	/**
	 * @return entity manager para realizar as operacoes com o banco de dados
	 */
	public EntityManager getEntityManager();
}
