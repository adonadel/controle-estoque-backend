package br.com.senac.api.entidades;

import br.com.senac.api.utils.TipoMovimentacaoEstoque;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "movimentacao_estoque")
public class MovimentacaoEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estoque_id")
    private Estoque estoque;

    @Column(nullable = false)
    private Long quantidade;

    @Enumerated(EnumType.STRING)
    private TipoMovimentacaoEstoque tipo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public TipoMovimentacaoEstoque getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimentacaoEstoque tipo) {
        this.tipo = tipo;
    }
}
