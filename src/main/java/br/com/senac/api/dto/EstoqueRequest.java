package br.com.senac.api.dto;

public class EstoqueRequest {
    private Long id;

    private ProdutosRequest produto;
    private LojasRequest loja;
    private Long quantidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProdutosRequest getProduto() {
        return produto;
    }

    public void setProduto(ProdutosRequest produto) {
        this.produto = produto;
    }

    public LojasRequest getLoja() {
        return loja;
    }

    public void setLoja(LojasRequest loja) {
        this.loja = loja;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }
}
