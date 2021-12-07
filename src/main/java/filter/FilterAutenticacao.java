/*******************************************
 * Em 12/11/2011 19:41
 * 
 * Responsável :  Marcelino F Sousa
 *  
 * Objetivo : Filtro responsável por controlar todas as ações durante a autenticação do sistema, tornando as 
 *      requisições e o acesso para as páginas web mais refinada
 * É a porta de entrada do sistema
 * 
 * */
package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import connection.SingleConnectionBanco;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import model.Login;


@WebFilter(urlPatterns = {"/principal/*"}) /* Intercepta toas as requisições que vierem do projeto ou mapeamento */
public class FilterAutenticacao implements Filter {

	private String contextPath;
	private static Connection connection ;
  
    public FilterAutenticacao() {
  
    }

    /* Encerra os processos quando o servidor é parado */
    /* Mata todos os processo de conexão com o banco */
	public void destroy() {
        try {
			connection.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
	}

	/* Intercepta as requisições e as respostas no sistema 
	 * Tudo o que fizer no sistema vai passar por aqui
	 * Validação de autenticação
	 * Dar commit e rolback de transações do banco
	 * Valida e fazer o redirecionamento da pagina
	 * */
	public void doFilter(ServletRequest request, 
			 ServletResponse response, 
			 FilterChain chain) throws IOException, ServletException {
		try {
			System.out.println("doFilter inicio ");
			
			//HttpServletResponse res = (HttpServletResponse) response;
	        HttpServletRequest req = (HttpServletRequest) request ;
	        HttpSession session = req.getSession() ;
	        
	        //Login login = (Login) session.getAttribute("usuario"); 
	        String login = (String) session.getAttribute("usuario") ;     
	        String urlParaAutenticar = req.getServletPath() ; /*url que está sendo acessada */
	        /* validar se está logado senão redireciona para a tela de login */
	        
	        if ( login == null  && !urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) {
	        	/* Usuário não está logado no sistema */
	        	System.out.println("doFilter if login ... ");
	        	
	        	RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url="+urlParaAutenticar) ;
	        	
	        	
	        	request.setAttribute("msg", "Por favor realiza o login!");
	        	redireciona.forward(request, response);
	        	return; /* Para a execução e redireciona para o login */
	        	
	        } else {
	        	System.out.println("doFilter else if login ... ");
	        	
	        	chain.doFilter(request, response);
	        }
	        connection.commit() ; /* Deu tudo certo, comita as alterações no banco de dados */
		} catch ( Exception e ) {
			e.printStackTrace();
			
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp") ;
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
			
			try {
				connection.rollback();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	/* Inicia os processos ou recursos quando o servidor sobe o projeto 
	 * Inicia conexão com o banco */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		 this.contextPath = filterConfig.getServletContext().getContextPath();
		 
		 connection = SingleConnectionBanco.getConnection() ;
		 
	}

}
