package br.ucsal.arqui.controller;
import br.ucsal.arqui.servicos.LivroService;
import br.ucsal.arqui.model.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/livros")


public class LivroController {

    @Autowired
    private LivroService livroService;

   /* @PostMapping
    public ResponseEntity<Livro> createLivro(@RequestBody Livro livro) {
        Livro novoLivro = livroService.adicionarLivro(livro);
        return ResponseEntity.ok(novoLivro);
    }*/

    @PostMapping
    public Livro adicionarLivro(@RequestBody Livro livro) {
        return livroService.adicionarLivro(livro);
    }

    @GetMapping
    public List<Livro> obterLivros() {
        return livroService.obterLivros();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> getLivroById(@PathVariable Long id) {
        Livro livro = livroService.getLivroById(id);
        return ResponseEntity.ok(livro);
    }

/*    @GetMapping("/{id}")
    public ResponseEntity<Livro> obterLivroById(@PathVariable Long id) {
        Livro livro = livroService.getLivroById(id).orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado"));
        return ResponseEntity.ok().body(livro);
    }*/

  /*  @PostMapping
    public Livro adicionarLivro(@RequestBody Livro livro) {
        return livroService.adicionarLivro(livro);
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizarLivro(@PathVariable Long id,
                                                @RequestBody Livro detalhesLivro) {
        Livro livro = livroService.atualizarLivro(id, detalhesLivro);
        return ResponseEntity.ok(livro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable Long id) {
        livroService.deletarLivro(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Livro> atualizarStatus(@PathVariable Long id, @RequestParam boolean disponivel) {
        Livro atualizarLivro = livroService.atualizarStatus(id, disponivel);
        return ResponseEntity.ok(atualizarLivro);
    }

    /*@PutMapping("/status/{id}")
    public ResponseEntity<Livro> atualizarStatus(@PathVariable Long id, @RequestParam boolean disponivel) {
        Livro atualizarLivro = livroService.atualizarStatus(id, disponivel);
        return ResponseEntity.ok(atualizarLivro);
    }*/


}

//subindo