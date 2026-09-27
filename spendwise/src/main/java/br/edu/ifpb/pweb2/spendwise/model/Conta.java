package br.edu.ifpb.pweb2.spendwise.model;

import br.edu.ifpb.pweb2.spendwise.enums.TipoConta;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "contas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private TipoConta tipo;

    private Integer diaFechamento;

    @ManyToOne
    @JoinColumn(name = "correntista_id")
    private Correntista correntista;
}