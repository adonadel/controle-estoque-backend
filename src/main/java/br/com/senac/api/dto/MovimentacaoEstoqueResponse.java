package br.com.senac.api.dto;

import br.com.senac.api.entidades.Estoque;
import br.com.senac.api.utils.TipoMovimentacaoEstoque;

public class MovimentacaoEstoqueResponse {

    private Long id;
    private Estoque estoque;
    private Long quantidade;
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
