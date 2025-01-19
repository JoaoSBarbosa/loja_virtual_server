package com.barbosacode.lojavirtual.models;
import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "log_sistema")
@SequenceGenerator(name = "seq_log_sistema", sequenceName = "seq_log_sistema", allocationSize = 1, initialValue = 1)
public class LogSistema {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_log_sistema")
    private Long id;
    @Column(name = "descricao_log", nullable = false)
    private String descricaoLog;

    @Column(name = "data_registro_log", nullable = false)
    private LocalDate dataLog;

    @Column(name = "hora_registro_log")
    private Time horaLog;

    @Column(name = "id_usuario")
    private Long idUsuario;


    @ManyToOne(targetEntity = Usuario.class)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", insertable = false, updatable = false,
    foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_log_usuario"))
    private Usuario usuario;

    public LogSistema(){}

    public LogSistema(Long id, String descricaoLog, LocalDate dataLog, Time horaLog, Long idUsuario, Usuario usuario) {
        this.id = id;
        this.descricaoLog = descricaoLog;
        this.dataLog = dataLog;
        this.horaLog = horaLog;
        this.idUsuario = idUsuario;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricaoLog() {
        return descricaoLog;
    }

    public void setDescricaoLog(String descricaoLog) {
        this.descricaoLog = descricaoLog;
    }

    public LocalDate getDataLog() {
        return dataLog;
    }

    public void setDataLog(LocalDate dataLog) {
        this.dataLog = dataLog;
    }

    public Time getHoraLog() {
        return horaLog;
    }

    public void setHoraLog(Time horaLog) {
        this.horaLog = horaLog;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof LogSistema)) return false;
        LogSistema that = (LogSistema) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "LogSistema{" +
                "id=" + id +
                ", descricaoLog='" + descricaoLog + '\'' +
                ", dataLog=" + dataLog +
                ", horaLog=" + horaLog +
                ", idUsuario=" + idUsuario +
                ", usuario=" + usuario +
                '}';
    }
}
