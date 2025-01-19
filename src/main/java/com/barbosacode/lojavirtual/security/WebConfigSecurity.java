package com.barbosacode.lojavirtual.security;

import com.barbosacode.lojavirtual.services.UserDetailServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpSessionListener;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebConfigSecurity extends WebSecurityConfigurerAdapter implements HttpSessionListener {

    @Autowired
    private UserDetailServiceImplementation userDetailServiceImplementation;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .disable() /* para entrar na tela de login*/
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/index").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                /*REDIRECIONA OU DA UM RETORNO PARA INDEX QUANDO O USER DESLOGAR DO SISTEMA*/

                .anyRequest().authenticated().and().logout().logoutSuccessUrl("/index")

                /* Mapear a parte de logout do sistema */

                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))

                /* Filtra as requisções para login de JWT*/
                .and().addFilterAfter(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)

                .addFilterBefore(new JWTApiAutenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    //    Consulta o user no banco com spring security
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // utilizando o serviço UserDetailServiceImplementation para fazer a busca no banco atraves do método loadUserByUsername
        auth.userDetailsService(userDetailServiceImplementation).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers(HttpMethod.GET, "/acessos/**")
//                .antMatchers(HttpMethod.POST, "/acessos/**")
//                .antMatchers(HttpMethod.PUT, "/acessos/**")
//                .antMatchers(HttpMethod.DELETE, "/acessos/**");

    }
}
