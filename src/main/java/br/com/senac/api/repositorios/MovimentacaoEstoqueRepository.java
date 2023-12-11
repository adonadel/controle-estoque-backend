package br.com.senac.api.repositorios;

import br.com.senac.api.entidades.MovimentacaoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimentacaoEstoqueRepository extends JpaRepository<MovimentacaoEstoque, Long> {

    @Query("select me " +
            "from movimentacao_estoque me " +
            "join estoque e on e.id = me.estoque.id " +
            "join produtos p on p.id = e.produto.id " +
            "join lojas j on j.id = e.loja.id " +
            "where me.estoque.id = ?1")
    public List<MovimentacaoEstoque> findAllByEstoqueId(@Param("estoque_id") Long estoqueId);
}
