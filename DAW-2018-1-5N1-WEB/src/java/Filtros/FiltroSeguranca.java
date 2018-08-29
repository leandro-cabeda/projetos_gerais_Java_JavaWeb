/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Filtros;

import Controles.ControleLogin;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Leandro
 */
@WebFilter(urlPatterns = "/privado/*")
public class FiltroSeguranca implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest=(HttpServletRequest)request;
        HttpServletResponse httpResponse=(HttpServletResponse)response;
        HttpSession sessao=httpRequest.getSession();
        String contextPath=httpRequest.getContextPath();
        ControleLogin controlelogin=(ControleLogin)sessao.getAttribute("controlelogin");
        if(controlelogin==null || controlelogin.getUsuarioLogado()==null)
        {
            //httpResponse.sendRedirect(contextPath +"/login.xhtml"); 
          // contexPath seria a localização que o usuario se encontra no browser da página.
          // sendRedirect rediciona para página.
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        
    }

   
    
}
