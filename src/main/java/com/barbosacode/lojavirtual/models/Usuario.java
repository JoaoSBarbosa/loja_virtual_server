package com.barbosacode.lojavirtual.models;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Data
@Entity
@Table(name = "usuario")
@SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", allocationSize = 1, initialValue = 1)
public class Usuario implements Serializable, UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
    private Long id;
    private String login;
    private String senha;
    @Temporal(TemporalType.DATE)
    private Date dataAtualSenha;

    @OneToMany(fetch = FetchType.LAZY) // Carrega os acessos apenas quando necessario
    @JoinTable(
            name = "usuario_acesso",
            uniqueConstraints = @UniqueConstraint(columnNames = {"id_usuario", "id_acesso"}, name = "unique_acesso_user"),
            joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id", table = "usuario", unique = false, foreignKey = @ForeignKey(name = "usuario_fk", value = ConstraintMode.CONSTRAINT)),
            inverseJoinColumns = @JoinColumn(name = "id_acesso", referencedColumnName = "id", table = "acesso", unique = false, foreignKey = @ForeignKey(name = "acesso_fk", value = ConstraintMode.CONSTRAINT)))
    private List<Acesso> acessos;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.acessos;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
