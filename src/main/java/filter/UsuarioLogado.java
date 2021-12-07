/*******************************************
 * Em 12/11/2011
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

import entidades.Usuario;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(filterName = "UsuarioLogado", urlPatterns = {"/logado/*"})
public class UsuarioLogado implements Filter {

    private String contextPath;

    public UsuarioLogado() {
    }

    @Override
    public void doFilter(ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        Usuario u = (Usuario) session.getAttribute("usuarioLogado");
        if (u == null) {
            session.invalidate();
            res.sendRedirect(contextPath + "/index.jsp");
        } else {
            res.setHeader("Cache-control", "no-cache, no-store");
            res.setHeader("Pragma", "no-cache");
            res.setHeader("Expires", "-1");
            chain.doFilter(request, response);
        }
    }
    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) {
        this.contextPath = filterConfig.getServletContext().getContextPath();
    }

}
