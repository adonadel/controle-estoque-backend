package br.com.senac.api.controller;

import br.com.senac.api.dto.ProdutosRequest;
import br.com.senac.api.dto.ProdutosResponse;
import br.com.senac.api.entidades.Produtos;
import br.com.senac.api.mappers.ProdutosMapper;
import br.com.senac.api.repositorios.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {
    @Autowired
    private ProdutosRepository produtosRepository;

    @GetMapping("/")
    public ResponseEntity<List<ProdutosResponse>> carregarProdutos()
    {
        List<Produtos> retorno = produtosRepository.findAll();

        List<ProdutosResponse> out = retorno
                .stream()
                .map(ProdutosMapper::produtosToProdutosResponse)
                .toList();

        return ResponseEntity.ok().body(out);
    }

    @PostMapping("/")
    public ResponseEntity<ProdutosResponse> criarProduto(@RequestBody ProdutosRequest produto)
    {
        Produtos retorno = produtosRepository.save(ProdutosMapper.produtosRequestToProdutos(produto));

        return ResponseEntity.status(HttpStatus.CREATED).body(ProdutosMapper.produtosToProdutosResponse(retorno));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id)
    {
        produtosRepository.deleteById(id);

        return ResponseEntity.ok(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutosResponse> atualizarProduto(@PathVariable Long id, @RequestBody ProdutosRequest produto)
    {
        Produtos retorno = produtosRepository.findById(id).map(record -> {
            record.setNome(produto.getNome());
            record.setDescricao(produto.getDescricao());
            record.setCodigoEan(produto.getCodigoEan());
            return produtosRepository.save(record);
        }).get();

        return ResponseEntity.ok(ProdutosMapper.produtosToProdutosResponse(retorno));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutosResponse> carregarProdutoById(@PathVariable Long id)
    {
        Produtos produto = produtosRepository.findById(id).get();

        return ResponseEntity.ok().body(ProdutosMapper.produtosToProdutosResponse(produto));
    }

    @GetMapping("/{nome}/buscar")
    @CrossOrigin
    public ResponseEntity<List<ProdutosResponse>> carregarProdutoByNome(@PathVariable String nome)
    {
        List<Produtos> produtos = produtosRepository.findByNome(nome);

        List<ProdutosResponse> out = produtos
                .stream()
                .map(ProdutosMapper :: produtosToProdutosResponse)
                .toList();

        return ResponseEntity.ok().body(out);
    }
}
