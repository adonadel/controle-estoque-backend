package br.com.senac.api.mappers;

import br.com.senac.api.dto.EstoqueRequest;
import br.com.senac.api.dto.EstoqueResponse;
import br.com.senac.api.entidades.Estoque;
import br.com.senac.api.entidades.Produtos;

public class EstoqueMapper {

    public static Estoque estoqueRequestToEstoque (EstoqueRequest estoque)
    {
        Estoque out = new Estoque();

        out.setQuantidade(estoque.getQuantidade());
        out.setLoja(LojasMapper.lojasRequestToLojas(estoque.getLoja()));
        out.setProduto(ProdutosMapper.produtosRequestToProdutos(estoque.getProduto()));

        return out;
    }

    public static EstoqueResponse estoqueToEstoqueResponse (Estoque estoque)
    {
        EstoqueResponse out = new EstoqueResponse();

        out.setQuantidade(estoque.getQuantidade());
        out.setLoja(LojasMapper.lojasToLojasResponse(estoque.getLoja()));
        out.setProduto(ProdutosMapper.produtosToProdutosResponse(estoque.getProduto()));
        out.setId(estoque.getId());

        return out;
    }

}
