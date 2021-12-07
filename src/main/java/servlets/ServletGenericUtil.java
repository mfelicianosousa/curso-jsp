package servlets;

import java.io.Serializable;

import dao.DAOUsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ServletGenericUtil implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	
	
	public Long getUserLogado(HttpServletRequest request) throws Exception {
		
        HttpSession session = request.getSession() ;
        String login = (String) session.getAttribute("usuario") ;   
		return daoUsuarioRepository.consultaUsuario(login).getId();
	}
}
