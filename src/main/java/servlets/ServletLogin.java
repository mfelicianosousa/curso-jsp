package servlets;

import java.io.IOException;

import dao.DAOLoginRepository;
import dao.DAOUsuarioRepository;
import entidades.Usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Login;

/* O chamado Controller são as servlets ou ServletLoginController*/
@WebServlet( urlPatterns = {"/principal/ServletLogin","/ServletLogin"} ) /* mapeamento de URL que vem da tela */
public class ServletLogin extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    DAOLoginRepository daoLoginRepository = new DAOLoginRepository() ;
    DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository() ;
    
    public ServletLogin() {
       
    }

    /* Recebe os dados pela url em parametros */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String acao = request.getParameter("acao");
		
		if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("logout")) {
			request.getSession().invalidate();  // invalida a sessão 
			RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp") ;
			redirecionar.forward(request, response);
			
		} else {
		    doPost(request, response) ;
		}
	}

	/* Recebe os dados enviadors por um formulário */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parmLogin = request.getParameter("login");
		String parmSenha = request.getParameter("senha");
		String url = request.getParameter("url");
		
		try {
			
			System.out.println("Entrei no doPost");
			
			
			if (parmLogin !=null && !parmLogin.isEmpty() && parmSenha != null && !parmSenha.isEmpty()) {
				
				Usuario usuario = new Usuario();
				
				Login login = new Login() ;
				
				login.setLogin( parmLogin );
				login.setSenha( parmSenha );
				
				if ( daoLoginRepository.validarAutenticacao(login)) {
					
					usuario = daoUsuarioRepository.consultaUsuario(parmLogin);
					
					System.out.println("doPost Usuario e senha validos ");
					
					request.getSession().setAttribute("usuario", login.getLogin() );
					request.getSession().setAttribute("isAdministrador", usuario.getIsAdministrador()) ;
			
					if (url == null || url.equals("null")) {
						url = "principal/principal.jsp" ;
						System.out.println("doPost url passada "+url);
						
					} 
					System.out.println("doPost if login *"+url+"*");
					
					RequestDispatcher redirecionar = request.getRequestDispatcher(url) ;
					request.setAttribute("msg", "Login e senha autorizado !");
					redirecionar.forward(request, response);			
					
				} else {
					System.out.println("doPost Entrei no ELSE IF login");
					RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp") ;
					request.setAttribute("msg", "Informe o login e a senha corretamente!");
					redirecionar.forward(request, response);			
				}
				
			} else {
				System.out.println("doPost Entrei no ELSE IF parmLogin");
				RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp") ;
				request.setAttribute("msg", "Informe o login e a senha corretamente!");
				redirecionar.forward(request, response);																																																									
			}
		} catch (Exception e ) {
			
			e.printStackTrace() ;
			
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp") ;
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
		
		
		
		
	}

}
