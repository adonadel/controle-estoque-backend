package br.com.senac.api.mappers;

import br.com.senac.api.dto.LojasRequest;
import br.com.senac.api.dto.LojasResponse;
import br.com.senac.api.entidades.Lojas;

public class LojasMapper {

    public static Lojas lojasRequestToLojas (LojasRequest loja)
    {
        Lojas out = new Lojas();

        out.setId(loja.getId());
        out.setNome(loja.getNome());
        out.setResponsavel(loja.getResponsavel());
        out.setCnpj(loja.getCnpj());

        return out;
    }

    public static LojasResponse lojasToLojasResponse (Lojas loja)
    {
        LojasResponse out = new LojasResponse();

        out.setNome(loja.getNome());
        out.setResponsavel(loja.getResponsavel());
        out.setCnpj(loja.getCnpj());
        out.setId(loja.getId());

        return out;
    }

}
