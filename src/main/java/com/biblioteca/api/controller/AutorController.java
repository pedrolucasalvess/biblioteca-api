package com.biblioteca.api.controller;

import com.biblioteca.api.model.Autor;
import com.biblioteca.api.repository.AutorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController indica que esta classe é um controlador REST
// Retorna dados (JSON) em vez de views HTML
@RestController
// @RequestMapping define o caminho base para todos os endpoints deste controller
@RequestMapping("/autores")
public class AutorController {

    // @Autowired injeta automaticamente uma instância do AutorRepository
    // Isso é chamado de Injeção de Dependência
    @Autowired
    private AutorRepository autorRepository;

    // @PostMapping mapeia requisições HTTP POST
    // @Valid ativa as validações definidas na classe Autor
    // @RequestBody indica que os dados vêm no corpo da requisição (JSON)
    @PostMapping
    public ResponseEntity<Autor> criarAutor(@Valid @RequestBody Autor autor) {
        Autor novoAutor = autorRepository.save(autor);
        return ResponseEntity.status(201).body(novoAutor);
    }

    // @GetMapping mapeia requisições HTTP GET
    // Retorna a lista de todos os autores
    @GetMapping
    public ResponseEntity<List<Autor>> listarAutores() {
        List<Autor> autores = autorRepository.findAll();
        return ResponseEntity.ok(autores);
    }

    // @PathVariable captura o ID da URL (ex: /autores/1)
    @GetMapping("/{id}")
    public ResponseEntity<Autor> buscarAutorPorId(@PathVariable Long id) {
        return autorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // @PutMapping mapeia requisições HTTP PUT (atualização)
    @PutMapping("/{id}")
    public ResponseEntity<Autor> atualizarAutor(@PathVariable Long id,
                                                 @Valid @RequestBody Autor autorAtualizado) {
        return autorRepository.findById(id).map(autor -> {
            autor.setNome(autorAtualizado.getNome());
            autor.setNacionalidade(autorAtualizado.getNacionalidade());
            Autor salvo = autorRepository.save(autor);
            return ResponseEntity.ok(salvo);
        }).orElse(ResponseEntity.notFound().build());
    }

    // @DeleteMapping mapeia requisições HTTP DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAutor(@PathVariable Long id) {
        if (!autorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        autorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
