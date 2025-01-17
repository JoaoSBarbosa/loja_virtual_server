package com.barbosacode.lojavirtual.security;

import org.springframework.stereotype.Service;

/*
 * Cria e retorna codigo JWT par autenticação
 */
@Service
public class JWTTokenAuthenticationService {


    //    Tempo de válidade token 2 dias
    private static final long EXPIRATION_TIME = 172800000;
    // senha secreta para ser utilizada na criptografia - Chave
    private static final String SECRET = "BarbosaCode_W0lf$2025!";

    private static final String TOKEN_PREFIX = "Bearer";
}
