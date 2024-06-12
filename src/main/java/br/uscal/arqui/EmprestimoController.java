package br.uscal.arqui;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/loans")
public class EmprestimoController {
    @Autowired
    private EmprestimoService emprestimoService;

    @GetMapping
    public List<Emprestimo> obterTodosEmprestimos() {
        return emprestimoService.obterEmprestimos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> obterEmprestimoById(@PathVariable Long id) {
        Emprestimo emprestimo = emprestimoService.getLoanById(id).orElseThrow(() -> new ResourceNotFoundException("Loan not found"));
        return ResponseEntity.ok().body(emprestimo);
    }

    @PostMapping
    public Emprestimo cadastrarEmprestimo(@RequestParam Long livroId, @RequestParam Long usuarioId) {
        return emprestimoService.registrarEmprestimo(livroId, usuarioId);
    }

    @PutMapping("/{id}/retorno")
    public ResponseEntity<Emprestimo> devolucao(@PathVariable Long id) {
        Emprestimo atualizarEmprestimo = EmprestimoService.retornarEmprestimo(id);
        return ResponseEntity.ok(atualizarEmprestimo);
    }

}
