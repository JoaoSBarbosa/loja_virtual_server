package com.barbosacode.lojavirtual.security;
import com.barbosacode.lojavirtual.config.ApplicationContextLoad;
import com.barbosacode.lojavirtual.exceptions.ControllerNotFoundException;
import com.barbosacode.lojavirtual.models.Usuario;
import com.barbosacode.lojavirtual.repositories.UsuarioRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.crypto.SecretKey;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SignatureException;
import java.util.Arrays;
import java.util.Date;

/**
 * Serviço responsável por gerenciar a autenticação via JWT (JSON Web Token).
 * Inclui métodos para geração e validação de tokens.
 */
@Service
public class JWTTokenAuthenticationService {

    /* Token tem validade de 10 dias */
    private static final long EXPIRATION_TIME = 864000000;
//    private static final long EXPIRATION_TIME = 1;
    private static final String SECRET = "BarbosaCodeW0lf2025";
    private static final String TOKEN_PREFIX = "Bearer";
    private static final String HEADER_STRING = "Authorization";

    /**
     * Método responsável por gerar o token JWT e adicioná-lo ao cabeçalho da resposta HTTP.
     *
     * @param response Objeto HttpServletResponse onde o token será adicionado.
     * @param username Nome do usuário que será utilizado como "subject" do token.
     * @throws Exception Caso ocorra algum erro na geração do token.
     */
    public void generateToken(HttpServletResponse response, String username) throws Exception {
        // Preenche ou corta a chave para garantir que ela tenha 64 bytes (512 bits)
        byte[] secretBytes = SECRET.getBytes();
        if (secretBytes.length < 64) {
            // Se a chave for menor que 64 bytes, preenche com zeros
            byte[] paddedSecret = new byte[64];
            System.arraycopy(secretBytes, 0, paddedSecret, 0, secretBytes.length);
            secretBytes = paddedSecret;
        } else if (secretBytes.length > 64) {
            // Se a chave for maior que 64 bytes, corta para 64 bytes
            secretBytes = Arrays.copyOf(secretBytes, 64);
        }

        SecretKey secretKey = new SecretKeySpec(secretBytes, SignatureAlgorithm.HS512.getJcaName());

        // 1. Criação do token JWT
        String JWT = Jwts.builder()
                .setSubject(username) // Define o "subject" do token como o nome do usuário
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Define a data de expiração do token
                .signWith(secretKey) // Assina o token com a chave corrigida
                .compact(); // Finaliza e retorna o token em formato compactado

        // 2. Adiciona o prefixo "Bearer" ao token para padronização
        String token = TOKEN_PREFIX + " " + JWT;

        // 3. Adiciona o token ao cabeçalho da resposta HTTP com o nome definido em HEADER_STRING
        response.addHeader(HEADER_STRING, token);
        // 4. Adiciona o token ao corpo da resposta (POSTMAN)
        releaseCors(response);

        response.getWriter().write("{\"Authorization\":\"" + token + "\"}");
    }

    /**
     * Valida o token JWT recebido na requisição e retorna uma instância de autenticação.
     *
     * @param request  Objeto HttpServletRequest contendo o token no cabeçalho.
     * @param response Objeto HttpServletResponse para configurar a resposta, se necessário.
     * @return Objeto Authentication representando o usuário autenticado.
     */
    public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Recupera o token do cabeçalho da requisição
        String token = request.getHeader(HEADER_STRING);

        try {
            // Verifica se o token é válido e começa com o prefixo esperado
            if (token != null && token.startsWith(TOKEN_PREFIX)) {
                String cleanToken = token.replace(TOKEN_PREFIX, "").trim();

                // Preenche ou corta a chave para garantir que ela tenha 64 bytes (512 bits)
                byte[] secretBytes = SECRET.getBytes();
                if (secretBytes.length < 64) {
                    byte[] paddedSecret = new byte[64];
                    System.arraycopy(secretBytes, 0, paddedSecret, 0, secretBytes.length);
                    secretBytes = paddedSecret;
                } else if (secretBytes.length > 64) {
                    secretBytes = Arrays.copyOf(secretBytes, 64);
                }

                SecretKey secretKey = new SecretKeySpec(secretBytes, SignatureAlgorithm.HS512.getJcaName());

                // Decodifica o token e extrai o "subject" (usuário)
                String user = Jwts.parserBuilder()
                        .setSigningKey(secretKey) // Usa a chave gerada para verificar o token
                        .build()
                        .parseClaimsJws(cleanToken)
                        .getBody()
                        .getSubject();

                if (user != null) {
                    // Busca o usuário no banco de dados
                    Usuario usuario = ApplicationContextLoad
                            .getApplicationContext()
                            .getBean(UsuarioRepository.class)
                            .findUserByLogin(user);

                    if (usuario != null) {
                        // Retorna a autenticação do usuário com suas permissões
                        return new UsernamePasswordAuthenticationToken(
                                usuario.getLogin(),
                                usuario.getPassword(),
                                usuario.getAuthorities()
                        );
                    } else {
                        // Usuário não encontrado
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        response.getWriter().write("{\"error\": \"Usuário não encontrado: " + user + "\"}");
                        return null;
                    }
                } else {
                    // Token inválido (sem usuário)
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("{\"error\": \"Erro ao autenticar o token\"}");
                    return null;
                }
            }
        } catch (ExpiredJwtException e){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"error\": \"O token está expirado, efetue o login e tente novamente.\"}");
        }
        catch (io.jsonwebtoken.security.SignatureException e) {
            // Token com assinatura inválida
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"error\": \"Token inválido: assinatura não corresponde.\"}");
        } catch (io.jsonwebtoken.JwtException e) {
            // Tratamento genérico para erros de JWT (token malformado, expirado, etc.)
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"error\": \"Erro ao processar o token: " + e.getMessage() + "\"}");
        }
        catch (Exception e) {
            // Tratamento genérico de outros erros
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"Erro inesperado: " + e.getMessage() + "\"}");
        }

        // Libera o CORS e retorna nulo
        releaseCors(response);
        return null;
    }


//    public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        // Recupera o token do cabeçalho da requisição
//        String token = request.getHeader(HEADER_STRING);
//        // Verifica se o token é válido e começa com o prefixo esperado
//        try {
//            if (token != null && token.startsWith(TOKEN_PREFIX)) {
//
//                String cleanToken = token.replace(TOKEN_PREFIX, "").trim();
//                // Preenche ou corta a chave para garantir que ela tenha 64 bytes (512 bits)
//                byte[] secretBytes = SECRET.getBytes();
//                if (secretBytes.length < 64) {
//                    byte[] paddedSecret = new byte[64];
//                    System.arraycopy(secretBytes, 0, paddedSecret, 0, secretBytes.length);
//                    secretBytes = paddedSecret;
//                } else if (secretBytes.length > 64) {
//                    secretBytes = Arrays.copyOf(secretBytes, 64);
//                }
//
//                SecretKey secretKey = new SecretKeySpec(secretBytes, SignatureAlgorithm.HS512.getJcaName());
//
//                // Decodifica o token e extrai o "subject" (usuário)
//                String user = Jwts.parserBuilder()
//                        .setSigningKey(secretKey) // Usa a chave gerada para verificar o token
//                        .build().parseClaimsJws(cleanToken)
//                        .getBody()
//                        .getSubject();
//
//                if (user != null) {
//                    // Busca o usuário no banco de dados
//                    Usuario usuario = ApplicationContextLoad.getApplicationContext().getBean(UsuarioRepository.class).findUserByLogin(user);
//
//                    if (usuario != null) {
//                        // Retorna a autenticação do usuário com suas permissões
//                        return new UsernamePasswordAuthenticationToken(
//                                usuario.getLogin(),
//                                usuario.getPassword(),
//                                usuario.getAuthorities()
//                        );
//                    } else {
//                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                        response.getWriter().write("{\"error\": \"Usuário não encontrado: " + user + "\"}");
//                        return null;
//                    }
//                } else {
//                    response.getWriter().write("{\"error\": \"Erro ao autenticar o token\"}");
//                    return null;
//                }
//            }
//
//        }catch (SignatureException e) {
//            // Token com assinatura inválida
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().write("{\"error\": \"Token inválido: " + e.getMessage() + "\"}");
//        } catch (Exception e) {
//            // Tratamento genérico de outros erros
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            response.getWriter().write("{\"error\": \"Erro ao processar a autenticação: " + e.getMessage() + "\"}");
//        }finally {
//            releaseCors(response);
//            return null;
//        }
//
//    }

    /**
     * Configura os cabeçalhos de CORS (Cross-Origin Resource Sharing) para permitir requisições
     * de origens diferentes.
     *
     * @param response Objeto HttpServletResponse onde os cabeçalhos serão configurados.
     */
    private void releaseCors(HttpServletResponse response) {

        if (response.getHeader("Access-Control-Allow-Origin") == null) {
            response.setHeader("Access-Control-Allow-Origin", "*");
        }

        if (response.getHeader("Access-Control-Allow-Headers") == null) {
            response.setHeader("Access-Control-Allow-Headers", "*");
        }

        if (response.getHeader("Access-Control-Request-Headers") == null) {
            response.setHeader("Access-Control-Request-Headers", "*");
        }

        if (response.getHeader("Access-Control-Allow-Methods") == null) {
            response.setHeader("Access-Control-Allow-Methods", "*");
        }

    }

}
