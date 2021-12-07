/************************************************************ 
 * Em 12/11/2011 19:41
 * 
 * Responsável :  Marcelino F Sousa
 *  
 * Objetivo : Cadastrar Usuários de acesso ao sistema 
 * 
 * 
*/
package entidades;

import java.io.Serializable;
import java.util.Objects;

public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
    private String nome;
    private String login;
	private String email;
    private String senha;
	private boolean isAdministrador;
	private boolean isAtivo;
	
	public boolean isNovo() {
		if (this.id == null) {
			return true;
		}else if (this.id != null && this.id > 0) {
			return false ;
		}else {
			return id == null ;
		}
			
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public boolean getIsAdministrador() {
		return isAdministrador;
	}
	public void setAdministrador(boolean isAdministrador) {
		this.isAdministrador = isAdministrador;
	}
	public boolean getIsAtivo() {
		return isAtivo;
	}
	public void setAtivo(boolean isAtivo) {
		this.isAtivo = isAtivo;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}
   

}
