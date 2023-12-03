package br.com.senac.api.controller;

import br.com.senac.api.dto.LojasRequest;
import br.com.senac.api.dto.LojasResponse;
import br.com.senac.api.entidades.Lojas;
import br.com.senac.api.mappers.LojasMapper;
import br.com.senac.api.repositorios.LojasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/lojas")
public class LojasController {
    @Autowired
    private LojasRepository lojasRepository;

    @GetMapping("/")
    public ResponseEntity<List<LojasResponse>> carregarLojas()
    {
        List<Lojas> retorno = lojasRepository.findAll();

        List<LojasResponse> out = retorno
                        .stream()
                        .map(LojasMapper::lojasToLojasResponse)
                        .toList();

        return ResponseEntity.ok().body(out);
    }

    @PostMapping("/")
    @CrossOrigin
    public ResponseEntity<LojasResponse> criarLoja(@RequestBody LojasRequest lojas)
    {
        Lojas retorno = lojasRepository.save(LojasMapper.lojasRequestToLojas(lojas));

        return ResponseEntity.status(HttpStatus.CREATED).body(LojasMapper.lojasToLojasResponse(retorno));
    }

    @DeleteMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<Void> deletarLoja(@PathVariable Long id)
    {
        lojasRepository.deleteById(id);

        return ResponseEntity.ok(null);
    }

    @PutMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<LojasResponse> atualizarLoja(@PathVariable Long id, @RequestBody LojasRequest loja)
    {
        Lojas retorno = lojasRepository.findById(id).map(record -> {
            record.setNome(loja.getNome());
            record.setCnpj(loja.getCnpj());
            record.setRazaoSocial(loja.getRazaoSocial());
            record.setResponsavel(loja.getResponsavel());
            return lojasRepository.save(record);
        }).get();

        return ResponseEntity.ok(LojasMapper.lojasToLojasResponse(retorno));
    }

    @GetMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<LojasResponse> carregarLojaById(@PathVariable Long id)
    {
        Lojas loja = lojasRepository.findById(id).get();

        return ResponseEntity.ok().body(LojasMapper.lojasToLojasResponse(loja));
    }

    @GetMapping("/{nome}/buscar")
    @CrossOrigin
    public ResponseEntity<List<LojasResponse>> carregarProdutoByNome(@PathVariable String nome)
    {
        List<Lojas> lojas = lojasRepository.findByNome(nome);

        List<LojasResponse> out = lojas
                .stream()
                .map(LojasMapper :: lojasToLojasResponse)
                .toList();

        return ResponseEntity.ok().body(out);
    }
}
