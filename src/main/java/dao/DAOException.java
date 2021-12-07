/*******************************************
 * Em 12/11/2011
 * 
 * Responsável :  Marcelino F Sousa
 *  
 * Objetivo : Classe responsável por lançar exceções durante a aplicação, caso haja algum imprevisto
 * 
 * 
 * */
package dao;


public class DAOException extends Exception {

    public DAOException(Throwable cause) {
        super(cause);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException() {
        super();
    }

}
