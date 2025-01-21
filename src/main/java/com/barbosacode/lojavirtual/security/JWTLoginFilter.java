package com.barbosacode.lojavirtual.security;

import com.barbosacode.lojavirtual.models.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    /* Configuração do gerenciar de autenticação */
    public JWTLoginFilter(String url, AuthenticationManager authManager) {
        /* Obriga autenticar a URL*/
        super(new AntPathRequestMatcher(url));
        /*Gerenciador autenticação*/
        setAuthenticationManager(authManager);
    }


    /* Retorna o usuario ao processaar na autenticação */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        /*Obtento usuario na requsição*/
        Usuario usuario = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);

        /* retorna o usuario com login e senha*/
        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(usuario.getLogin(), usuario.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        super.successfulAuthentication(request, response, chain, authResult);
        try {
            new JWTTokenAuthenticationService().generateToken(response, authResult.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
//        super.unsuccessfulAuthentication(request, response, failed);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String errorMessage;
        if (failed instanceof BadCredentialsException) {
            errorMessage = "Credenciais inválidas. Por favor, verifique o nome de usuário e a senha.";
        } else {
            errorMessage = "Erro ao autenticar: " + failed.getMessage();
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("{\"error\": \"" + errorMessage + "\"}");    }}
