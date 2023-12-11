package br.com.senac.api.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import org.springframework.web.servlet.function.ServerRequest;

import java.util.List;

@Entity(name = "estoque")
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id", scope= ServerRequest.class)
    @ManyToOne
    @JoinColumn(name = "produtos_id")
    private Produtos produto;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id", scope=ServerRequest.class)
    @ManyToOne
    @JoinColumn(name = "lojas_id")
    private Lojas loja;

    @Column(nullable = false)
    private Long quantidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produtos getProduto() {
        return produto;
    }

    public void setProduto(Produtos produto) {
        this.produto = produto;
    }

    public Lojas getLoja() {
        return loja;
    }

    public void setLoja(Lojas loja) {
        this.loja = loja;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }
}
