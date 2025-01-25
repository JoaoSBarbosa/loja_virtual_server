package com.barbosacode.lojavirtual.models;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "usuario")
@SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", allocationSize = 1, initialValue = 1)
public class Usuario implements Serializable, UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
    private Long id;
    @Column(nullable = false)
    private String login;
    @Column(nullable = false)
    private String senha;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataAtualSenha;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_acesso",
            uniqueConstraints = @UniqueConstraint(columnNames = {"id_usuario", "id_acesso"}, name = "unique_acesso_user"),
            joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id", table = "usuario", unique = false,
                    foreignKey = @ForeignKey(name = "usuario_fk", value = ConstraintMode.CONSTRAINT)),
            inverseJoinColumns = @JoinColumn(name = "id_acesso", referencedColumnName = "id", table = "acesso",  unique = false,
                    foreignKey = @ForeignKey(name = "acesso_fk", value = ConstraintMode.CONSTRAINT)))
    private Set<Acesso> acessos;

    @ManyToOne
    @JoinColumn(name = "id_pessoa", nullable = false,
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,
                    name = "fk_acesso_usaurio_pessoa"))
    private Pessoa pessoa;

//    @OneToMany(fetch = FetchType.LAZY) // Carrega os acessos apenas quando necessario
//    @OneToMany(fetch = FetchType.LAZY) // Carrega os acessos apenas quando necessario
//    @JoinTable(
//            name = "usuario_acesso",
//            uniqueConstraints = @UniqueConstraint(
//                    columnNames = {"id_usuario", "id_acesso"},
//                    name = "unique_acesso_user"
//            ),
//            joinColumns = @JoinColumn(
//                    name = "id_usuario",
//                    referencedColumnName = "id",
//                    table = "usuario",
//                    unique = false,
//                    foreignKey = @ForeignKey(
//                            name = "usuario_fk",
//                            value = ConstraintMode.CONSTRAINT
//                    )
//            ),
//            inverseJoinColumns = @JoinColumn(
//                    name = "id_acesso",
//                    referencedColumnName = "id",
//                    table = "acesso",
//                    unique = false,
//                    foreignKey = @ForeignKey(
//                            name = "acesso_fk",
//                            value = ConstraintMode.CONSTRAINT
//                    )
//            )
//    )
//    private List<Acesso> acessos;


    public Usuario() {}

    public Usuario(Long id, Pessoa pessoa, String senha, String login, Date dataAtualSenha) {
        this.id = id;
        this.pessoa = pessoa;
        this.senha = senha;
        this.login = login;
        this.dataAtualSenha = dataAtualSenha;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDataAtualSenha() {
        return dataAtualSenha;
    }

    public void setDataAtualSenha(Date dataAtualSenha) {
        this.dataAtualSenha = dataAtualSenha;
    }

    public Set<Acesso> getAcessos() {
        return acessos;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", dataAtualSenha=" + dataAtualSenha +
                ", acessos=" + acessos +
                ", pessoa=" + pessoa +
                '}';
    }

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
