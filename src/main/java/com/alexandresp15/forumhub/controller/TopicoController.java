package com.alexandresp15.forumhub.controller;

import com.alexandresp15.forumhub.domain.topico.Topico;
import com.alexandresp15.forumhub.dto.*;
import com.alexandresp15.forumhub.repository.TopicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoRepository repository;

    public TopicoController(TopicoRepository repository) {
        this.repository = repository;
    }

    // CREATE
    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody DadosCadastroTopico dados,
                                       UriComponentsBuilder uriBuilder) {

        // validação manual
        if (dados.titulo() == null || dados.titulo().isBlank()) {
            return ResponseEntity.badRequest().body("Título é obrigatório");
        }

        if (dados.mensagem() == null || dados.mensagem().isBlank()) {
            return ResponseEntity.badRequest().body("Mensagem é obrigatória");
        }

        if (dados.autor() == null || dados.autor().isBlank()) {
            return ResponseEntity.badRequest().body("Autor é obrigatório");
        }

        if (dados.curso() == null || dados.curso().isBlank()) {
            return ResponseEntity.badRequest().body("Curso é obrigatório");
        }

        // validação de duplicidade
        if (repository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem())) {
            return ResponseEntity.badRequest()
                    .body("Já existe um tópico com o mesmo título e mensagem");
        }

        Topico topico = new Topico(
                dados.titulo(),
                dados.mensagem(),
                dados.autor(),
                dados.curso()
        );

        repository.save(topico);

        var uri = uriBuilder
                .path("/topicos/{id}")
                .buildAndExpand(topico.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(new DadosDetalhamentoTopico(topico));
    }

    // READ
    @GetMapping
    public ResponseEntity<List<DadosListagemTopico>> listar() {

        var lista = repository.findAll()
                .stream()
                .map(DadosListagemTopico::new)
                .toList();

        return ResponseEntity.ok(lista);
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<?> detalhar(@PathVariable Long id) {

        var topicoOptional = repository.findById(id);

        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var topico = topicoOptional.get();

        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    // UPDATE
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizar(@PathVariable Long id,
                                       @RequestBody DadosAtualizacaoTopico dados) {

        var topicoOptional = repository.findById(id);

        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var topico = topicoOptional.get();

        topico.atualizarInformacoes(
                dados.titulo(),
                dados.mensagem(),
                dados.curso(),
                dados.status()
        );

        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    // DELETE
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> excluir(@PathVariable Long id) {

        var topicoOptional = repository.findById(id);

        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        repository.delete(topicoOptional.get());

        return ResponseEntity.noContent().build();
    }
}