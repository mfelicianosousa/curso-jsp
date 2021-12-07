/*
 * Criado em : 14/11/2021
 * 
 * Responsável : Marcelino Feliciano de Sousa
 * 
 *  Objetivo : Esta classe será responsável por centralizar as lógicas de persistência em comum entre o grupo de entidades do contrato EntidadeBase.
 *  
 *  Exemplo para instanciar um objeto de DaoGenerico representado a entidade pessoa
 *  
 *  DaoGenerico<Pessoa> daoPessoa = new DaoGenerico<Pessoa>();
 *  
 *  
 *  
 * */
package dao;

import javax.persistence.EntityManager;

import connection.ConnectionFactory;
import entidades.EntidadeBase;

public class DaoGeneric <T extends EntidadeBase > {
	
	private static EntityManager manager = ConnectionFactory.getEntityManager();
	
	/*
	 *  Método capaz de realizar a busca se baseando pelo tipo de uma entidade modelo e sua chave primária, 
	 *  no caso o atributo ID das classes de modelo.
	 *  
	 *  Retorna uma entidade do tipo T. Observe que T é a entidade modelo que contratou os serviços de EntidadeBase.
	 *  
	 *  Exemplo : manager.find(Entidade.class, chavePrimaria);
	 *  
	 * */
	public T findById(Class<T> clazz, Long id){
		return manager.find(clazz, id);
	}
	
	public void saveOrUpdate(T obj){
		try{
			manager.getTransaction().begin();
			if(obj.getId() == null){
				manager.persist(obj);
			}else{
				manager.merge(obj);
			}
			manager.getTransaction().commit();
		}catch(Exception e){
			manager.getTransaction().rollback();
		}
	}
	
	
	public void remove(Class<T> clazz, Long id){
		T t = findById(clazz, id);
		try{
			manager.getTransaction().begin();
			manager.remove(t);
			manager.getTransaction().commit();
		}catch (Exception e) {
			manager.getTransaction().rollback();
		}
	}

}
