package br.com.senac.api.repositorios;

import br.com.senac.api.entidades.Lojas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LojasRepository extends JpaRepository<Lojas, Long> {
    @Query("select l from lojas l where l.nome like %:nome%")
    public List<Lojas> findByNome(@Param("nome") String nome);
}
