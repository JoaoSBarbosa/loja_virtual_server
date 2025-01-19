package com.barbosacode.lojavirtual.services;

import com.barbosacode.lojavirtual.models.Usuario;
import com.barbosacode.lojavirtual.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImplementation implements UserDetailsService {

    @Autowired private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findUserByLogin(login);

        if(usuario == null) {
            throw new UsernameNotFoundException("Usuário inexistente com o login informado: " + login);
        }
        // Garantir que os acessos sejam inicializados dentro da transação

        return new User(usuario.getLogin(), usuario.getPassword(), usuario.getAuthorities());
    }
}
