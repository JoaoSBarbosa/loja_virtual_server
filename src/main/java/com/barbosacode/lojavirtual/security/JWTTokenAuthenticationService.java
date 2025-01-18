package com.barbosacode.lojavirtual.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/*
 * Serviço responsável pela geração de tokens JWT para autenticação.
 */
@Service
public class JWTTokenAuthenticationService {

    // Tempo de validade do token definido como 2 dias (em milissegundos)
    private static final long EXPIRATION_TIME = 172800000;

    // Chave secreta utilizada para assinar o token (mantenha isso seguro)
    private static final String SECRET = "BarbosaCode_W0lf$2025!";

    // Prefixo do token para identificar o tipo de autenticação (Bearer Token)
    private static final String TOKEN_PREFIX = "Bearer";

    // Nome do cabeçalho onde o token será adicionado
    private static final String HEADER_STRING = "Authorization";

    /**
     * Método responsável por gerar o token JWT e adicioná-lo ao cabeçalho da resposta HTTP.
     *
     * @param response Objeto HttpServletResponse onde o token será adicionado.
     * @param username Nome do usuário que será utilizado como "subject" do token.
     * @throws Exception Caso ocorra algum erro na geração do token.
     */
    public void generateToken(HttpServletResponse response, String username) throws Exception {
        // 1. Criação do token JWT
        String JWT = Jwts.builder()
                .setSubject(username) // Define o "subject" do token como o nome do usuário
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Define a data de expiração do token
                .signWith(SignatureAlgorithm.HS512, SECRET) // Assina o token com o algoritmo HS512 e a chave secreta
                .compact(); // Finaliza e retorna o token em formato compactado

        // 2. Adiciona o prefixo "Bearer" ao token para padronização
        String token = TOKEN_PREFIX + " " + JWT;

        // 3. Adiciona o token ao cabeçalho da resposta HTTP com o nome definido em HEADER_STRING
        response.addHeader(HEADER_STRING, token);
        // 4. Adiciona o token ao corpo da resposta (POSTMAN)

        response.getWriter().write("{\"Authorization\":\"" + token + "\"}");
    }
}
