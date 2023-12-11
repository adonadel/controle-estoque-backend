package br.com.senac.api.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "produtos")
public class Produtos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String codigoEan;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String dimensoes;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "produtos_id")
    private List<Estoque> estoque;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoEan() {
        return codigoEan;
    }

    public void setCodigoEan(String codigoEan) {
        this.codigoEan = codigoEan;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Estoque> getEstoque() {
        return estoque;
    }

    public void setEstoque(List<Estoque> estoque) {
        this.estoque = estoque;
    }

    public String getDimensoes() {
        return dimensoes;
    }

    public void setDimensoes(String dimensoes) {
        this.dimensoes = dimensoes;
    }
}
