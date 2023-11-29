package br.com.senac.api.dto;

public class EstoqueResponse {
    private Long id;

    private ProdutosResponse produto;
    private LojasResponse loja;
    private Long quantidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProdutosResponse getProduto() {
        return produto;
    }

    public void setProduto(ProdutosResponse produto) {
        this.produto = produto;
    }

    public LojasResponse getLoja() {
        return loja;
    }

    public void setLoja(LojasResponse loja) {
        this.loja = loja;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }
}
