package br.com.senac.api.controller;

import br.com.senac.api.dto.EstoqueRequest;
import br.com.senac.api.dto.EstoqueResponse;
import br.com.senac.api.dto.MovimentacaoEstoqueResponse;
import br.com.senac.api.entidades.Estoque;
import br.com.senac.api.entidades.MovimentacaoEstoque;
import br.com.senac.api.mappers.EstoqueMapper;
import br.com.senac.api.mappers.MovimentacaoEstoqueMapper;
import br.com.senac.api.repositorios.EstoqueRepository;
import br.com.senac.api.repositorios.MovimentacaoEstoqueRepository;
import br.com.senac.api.utils.TipoMovimentacaoEstoque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/estoques")
public class EstoqueController {
    @Autowired
    private EstoqueRepository estoqueRepository;
    @Autowired
    private MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;

    @GetMapping("/")
    @CrossOrigin
    public ResponseEntity<List<EstoqueResponse>> carregarEstoque()
    {
        List<Estoque> retorno = estoqueRepository.findAll();

        List<EstoqueResponse> out = retorno
                .stream()
                .map(EstoqueMapper:: estoqueToEstoqueResponse)
                .toList();

        return ResponseEntity.ok().body(out);
    }

    @PostMapping("/")
    @CrossOrigin
    public ResponseEntity<EstoqueResponse> criarEstoque(@RequestBody EstoqueRequest estoque)
    {
        Estoque retorno = estoqueRepository.save(EstoqueMapper.estoqueRequestToEstoque(estoque));

        MovimentacaoEstoque movimentacaoEstoque = new MovimentacaoEstoque();
        movimentacaoEstoque.setEstoque(retorno);
        movimentacaoEstoque.setTipo(TipoMovimentacaoEstoque.ENTRADA);
        movimentacaoEstoque.setQuantidade(retorno.getQuantidade());

        movimentacaoEstoqueRepository.save(movimentacaoEstoque);

        return ResponseEntity.status(HttpStatus.CREATED).body(EstoqueMapper.estoqueToEstoqueResponse(retorno));
    }

    @PutMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<EstoqueResponse>atualizarEstoque(@PathVariable Long id, @RequestBody EstoqueRequest estoque)
    {
        Estoque retorno = estoqueRepository.findById(id).map(record -> {
            record.setQuantidade(estoque.getQuantidade());
            return estoqueRepository.save(record);
        }).get();

        return ResponseEntity.ok(EstoqueMapper.estoqueToEstoqueResponse(retorno));
    }

    @DeleteMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<Void> deletarEstoque(@PathVariable Long id)
    {
        estoqueRepository.deleteById(id);

        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<EstoqueResponse> carregarEstoqueById(@PathVariable Long id)
    {
        Estoque estoque = estoqueRepository.findById(id).get();

        EstoqueResponse out = EstoqueMapper.estoqueToEstoqueResponse(estoque);

        return ResponseEntity.ok().body(out);
    }

    @PostMapping("/{id}/entrada")
    @CrossOrigin
    public ResponseEntity<MovimentacaoEstoqueResponse> entradaEstoque(@PathVariable Long id, @RequestBody Estoque estoqueBody)
    {
        if (estoqueBody.getQuantidade() < 0) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        Estoque estoque = estoqueRepository.findById(id).get();

        Long quantidadeAtual = estoque.getQuantidade();

        estoque.setQuantidade(Long.sum(quantidadeAtual, estoqueBody.getQuantidade()));

        estoqueRepository.save(estoque);

        MovimentacaoEstoque movimentacaoEstoque = new MovimentacaoEstoque();
        movimentacaoEstoque.setEstoque(estoque);
        movimentacaoEstoque.setTipo(TipoMovimentacaoEstoque.ENTRADA);
        movimentacaoEstoque.setQuantidade(estoqueBody.getQuantidade());

        MovimentacaoEstoque retorno = movimentacaoEstoqueRepository.save(movimentacaoEstoque);

        return ResponseEntity.status(HttpStatus.CREATED).body(MovimentacaoEstoqueMapper.movimentacaoEstoqueToMovimentacaoEstoqueResponse(retorno));
    }

    @PostMapping("/{id}/saida")
    @CrossOrigin
    public ResponseEntity<MovimentacaoEstoqueResponse> saidaEstoque(@PathVariable Long id, @RequestBody Estoque estoqueBody)
    {
        Estoque estoque = estoqueRepository.findById(id).get();

        Long quantidadeAtual = estoque.getQuantidade();

        if (estoqueBody.getQuantidade() > quantidadeAtual || estoqueBody.getQuantidade() < 0) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        estoque.setQuantidade(quantidadeAtual - estoqueBody.getQuantidade());

        estoqueRepository.save(estoque);

        MovimentacaoEstoque movimentacaoEstoque = new MovimentacaoEstoque();
        movimentacaoEstoque.setEstoque(estoque);
        movimentacaoEstoque.setTipo(TipoMovimentacaoEstoque.SAIDA);
        movimentacaoEstoque.setQuantidade(estoqueBody.getQuantidade());

        MovimentacaoEstoque retorno = movimentacaoEstoqueRepository.save(movimentacaoEstoque);

        return ResponseEntity.status(HttpStatus.CREATED).body(MovimentacaoEstoqueMapper.movimentacaoEstoqueToMovimentacaoEstoqueResponse(retorno));
    }

    @GetMapping("/{id}/movimentacoes")
    @CrossOrigin
    public ResponseEntity<List<MovimentacaoEstoqueResponse>> carregarMovimentacaoEstoqueById(@PathVariable Long id)
    {
        List<MovimentacaoEstoque> retorno = movimentacaoEstoqueRepository.findAllByEstoqueId(id);

        List<MovimentacaoEstoqueResponse> out = retorno
                .stream()
                .map(MovimentacaoEstoqueMapper:: movimentacaoEstoqueToMovimentacaoEstoqueResponse)
                .toList();

        return ResponseEntity.ok().body(out);
    }
}
