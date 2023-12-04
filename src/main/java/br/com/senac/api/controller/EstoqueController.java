package br.com.senac.api.controller;

import br.com.senac.api.dto.EstoqueRequest;
import br.com.senac.api.dto.EstoqueResponse;
import br.com.senac.api.entidades.Estoque;
import br.com.senac.api.mappers.EstoqueMapper;
import br.com.senac.api.repositorios.EstoqueRepository;
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

    @GetMapping("/")
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
    public ResponseEntity<EstoqueResponse> criarEstoque(@RequestBody EstoqueRequest estoque)
    {
        Estoque retorno = estoqueRepository.save(EstoqueMapper.estoqueRequestToEstoque(estoque));

        return ResponseEntity.status(HttpStatus.CREATED).body(EstoqueMapper.estoqueToEstoqueResponse(retorno));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EstoqueResponse>atualizarEstoque(@PathVariable Long id, @RequestBody EstoqueRequest estoque)
    {
        Estoque retorno = estoqueRepository.findById(id).map(record -> {
            record.setQuantidade(estoque.getQuantidade());
            return estoqueRepository.save(record);
        }).get();

        return ResponseEntity.ok(EstoqueMapper.estoqueToEstoqueResponse(retorno));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEstoque(@PathVariable Long id)
    {
        estoqueRepository.deleteById(id);

        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstoqueResponse> carregarEstoqueById(@PathVariable Long id)
    {
        Estoque estoque = estoqueRepository.findById(id).get();

        EstoqueResponse out = EstoqueMapper.estoqueToEstoqueResponse(estoque);

        return ResponseEntity.ok().body(out);
    }
}
