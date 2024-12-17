package com.barbosacode.lojavirtual.models;

import lombok.*;

import javax.persistence.*;

@Data
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "forma_pagamento")
@SequenceGenerator(name = "seq_forma_pagamento", sequenceName = "seq_forma_pagamento", allocationSize = 1, initialValue = 1)
public class FormaPagamento {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_forma_pagamento")
    private Long id;

    @Column(nullable = false)
    private String descricao;
}
