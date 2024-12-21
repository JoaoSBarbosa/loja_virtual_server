package com.barbosacode.lojavirtual.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "log_sistema")
@SequenceGenerator(name = "seq_log_sistema", sequenceName = "seq_log_sistema", allocationSize = 1, initialValue = 1)
public class LogSistema {

    @Id
    @EqualsAndHashCode.Include
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
}
