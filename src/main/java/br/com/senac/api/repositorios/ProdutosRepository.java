package br.com.senac.api.repositorios;

import br.com.senac.api.entidades.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Long> {
    @Query("select p from produtos p where p.nome like %:nome%")
    public List<Produtos> findByNome(@Param("nome") String nome);
}
