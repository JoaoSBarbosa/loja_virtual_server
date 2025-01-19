package com.barbosacode.lojavirtual.security;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/* Intercepta as requiisções, tudo passa por aqui*/
public class JWTApiAutenticationFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        /* Estabeler autenticação do usario */
        Authentication authentication = new JWTTokenAuthenticationService().getAuthentication((HttpServletRequest) request, (HttpServletResponse) response);

        /* processo de autenticação com Spring security - usa classe do spring para ativar todas as classes implementadas*/
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);

    }
}
