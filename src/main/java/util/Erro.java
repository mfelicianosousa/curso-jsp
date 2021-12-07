/*******************************************
 * Em 12/11/2011
 * 
 * Responsável :  Marcelino F Sousa
 *  
 * Objetivo : Controlar os erros 
 * 
 * https://www.devmedia.com.br/javaserver-pages-autenticacao-de-login-com-jsp-e-postgresql/31849
 */
package util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class Erro implements Serializable {

    private final List<String> erros;

    public Erro() {
        erros = new ArrayList<>();
    }

    public Erro(String mensagem) {
        erros = new ArrayList<>();
        erros.add(mensagem);
    }

    public void add(String mensagem) {
        erros.add(mensagem);
    }

    public boolean isExisteErros() {
        return !erros.isEmpty();
    }

    public List<String> getErros() {
        return erros;
    }

}
