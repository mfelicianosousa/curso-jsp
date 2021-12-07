package servlets;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOUsuarioRepository;
import entidades.Usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletUsuarioController
 */
@WebServlet(urlPatterns = { "/ServletUsuarioController" })
public class ServletUsuarioController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository() ;
    
    public ServletUsuarioController() {

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       /* usado para consultar e deletar */
	   try {
		   String acao = request.getParameter("acao");
		   
		   if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {
			   String id = request.getParameter("id");
			   daoUsuarioRepository.deleteUsuario(id);
			   
			   List<Usuario> listarDadosUsuario = daoUsuarioRepository.listarUsuarios();
			
			   request.setAttribute( "listarDadosUsuario", listarDadosUsuario ) ;
			   request.setAttribute("msg","Excluido com sucesso") ;
			   request.getRequestDispatcher("principal/cad-usuario.jsp").forward(request, response) ;
						
		   } else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarAjax")) {
			   
			   String id = request.getParameter("id");
			   
			   daoUsuarioRepository.deleteUsuario(id);
			 
			   response.getWriter().write("Excluido com sucesso!") ;
			   
						
		   } else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("pesquisarPorNome")) {
			   
			   String nomePesquisa = request.getParameter( "nomePesquisa" );
			   List<Usuario> dadosJsonUsuario = daoUsuarioRepository.pesquisarPorNome( nomePesquisa );
			   
		       ObjectMapper mapper = new ObjectMapper();
		       String json = mapper.writeValueAsString( dadosJsonUsuario ) ;
		       response.getWriter().write(json) ;
			   
						
		   } else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase( "editarUsuario" )) {
			   
			   String id = request.getParameter( "id" ) ;
			   Usuario usuario = daoUsuarioRepository.pesquisarPorId( Long.parseLong( id ) );
			   
			   List<Usuario> listarDadosUsuario = daoUsuarioRepository.listarUsuarios();
		
			   request.setAttribute( "listarDadosUsuario", listarDadosUsuario ) ;
			   
			   request.setAttribute( "msg", "Usuário em edição" ) ;
			   request.setAttribute( "modelLogin", usuario ) ;
				request.getRequestDispatcher( "principal/cad-usuario.jsp" ).forward( request, response ) ;
			   
		   } else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase( "listarUsuario" )) {
			  
			   List<Usuario> listarDadosUsuario = daoUsuarioRepository.listarUsuarios();
			   request.setAttribute( "msg", "Usuários carregados" ) ;
			   request.setAttribute( "listarDadosUsuario", listarDadosUsuario ) ;
			   request.getRequestDispatcher( "principal/cad-usuario.jsp" ).forward( request, response ) ;
			   
		   }else {
			   
			   List<Usuario> listarDadosUsuario = daoUsuarioRepository.listarUsuarios();
				
			   request.setAttribute( "listarDadosUsuario", listarDadosUsuario ) ;
			   
			   request.getRequestDispatcher("principal/cad-usuario.jsp").forward( request, response ) ;
		   }
	   } catch (Exception e) {
			
			e.printStackTrace() ;
			
			RequestDispatcher redirecionar = request.getRequestDispatcher( "erro.jsp" ) ;
			request.setAttribute( "msg", e.getMessage() );
			redirecionar.forward( request, response );
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       /* Salvar e atualizar */
		String msgDescription = null ;
		
		try {
			String id = request.getParameter("id") ;
			String login = request.getParameter("login") ;
			String nome = request.getParameter("nome") ;
			String senha = request.getParameter("senha") ;
			String email = request.getParameter("email");
			String administrador = request.getParameter("isAdministrador");
			String ativo = request.getParameter("isAtivo");
			
		
			Usuario usuario = new Usuario() ;
			usuario.setId( id !=null && !id.isEmpty() ? Long.parseLong( id ): null );
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario.setNome(nome);
			usuario.setEmail(email);
			usuario.setAdministrador( Boolean.valueOf(administrador));
			usuario.setAtivo( Boolean.valueOf(ativo));
			
			/* Validar Login */
			if ( daoUsuarioRepository.UsuarioExists(usuario.getLogin()) && usuario.getId() == null) {
				msgDescription="Usuário já cadastrado com o login informado. Informe outro login.";
			} else {
				if (usuario.isNovo()) {
			        msgDescription ="Gravado com sucesso";
				}else {
			    	 msgDescription = "Atualizado com sucesso" ;
			     }
			        	   
				/* retorna o usuário que está sendo gravado */
				usuario = daoUsuarioRepository.gravarUsuario(usuario, 1L);
			}
			request.setAttribute("msg",msgDescription) ;
			request.setAttribute("modelLogin",usuario) ;
			request.getRequestDispatcher("principal/cad-usuario.jsp").forward(request, response) ;
					
		} catch (Exception e) {
			
			e.printStackTrace() ;
			
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp") ;
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
		
	}

}
