/********************************************
 * 
 * Em 12/11/2011
 * 
 * Responsável : Dev:  Marcelino F Sousa
 *  
 * Objetivo:  Interface responsável por conter métodos para recuperar as informações
 *   de diversas maneiras
 *   
 */
package dao;

import java.util.List;


public interface DAO<T> {
	
    public T getSingle(Object... chave);
    public List<T> getList();
    public List<T> getList(int top);

}