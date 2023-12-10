package br.com.senac.api.mappers;

import br.com.senac.api.dto.MovimentacaoEstoqueRequest;
import br.com.senac.api.dto.MovimentacaoEstoqueResponse;
import br.com.senac.api.entidades.MovimentacaoEstoque;

public class MovimentacaoEstoqueMapper {

    public static MovimentacaoEstoque movimentacaoEstoqueRequestToMovimentacaoEstoque (MovimentacaoEstoqueRequest movimentacaoEstoque)
    {
        MovimentacaoEstoque out = new MovimentacaoEstoque();

        out.setEstoque(movimentacaoEstoque.getEstoque());
        out.setQuantidade(movimentacaoEstoque.getQuantidade());


        return out;
    }

    public static MovimentacaoEstoqueResponse movimentacaoEstoqueToMovimentacaoEstoqueResponse (MovimentacaoEstoque movimentacaoEstoque)
    {
        MovimentacaoEstoqueResponse out = new MovimentacaoEstoqueResponse();



        return out;
    }
}
