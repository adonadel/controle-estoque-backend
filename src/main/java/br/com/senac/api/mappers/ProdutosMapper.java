package br.com.senac.api.mappers;

import br.com.senac.api.dto.ProdutosRequest;
import br.com.senac.api.dto.ProdutosResponse;
import br.com.senac.api.entidades.Produtos;

public class ProdutosMapper {

    public static Produtos produtosRequestToProdutos (ProdutosRequest produto)
    {
        Produtos out = new Produtos();

        out.setId(produto.getId());
        out.setNome(produto.getNome());
        out.setCodigoEan(produto.getCodigoEan());
        out.setDescricao(produto.getDescricao());

        return out;
    }

    public static ProdutosResponse produtosToProdutosResponse (Produtos produto)
    {
        ProdutosResponse out = new ProdutosResponse();

        out.setNome(produto.getNome());
        out.setCodigoEan(produto.getCodigoEan());
        out.setDescricao(produto.getDescricao());
        out.setId(produto.getId());

        return out;
    }

}
