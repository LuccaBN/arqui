package br.uscal.arqui;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
public class EmprestimoService {
    @Autowired
    private EmprestimoRepositorio emprestimoRepositorio;

    @Autowired
    private RestTemplate restTemplate;

    private final String catalogoServiceUrl = "http://localhost:8080/api/livros";

    public List<Emprestimo> obterEmprestimos() {
        return emprestimoRepositorio.findAll();
    }

    public Optional<Emprestimo> getLoanById(Long id) {
        return emprestimoRepositorio.findById(id);
    }

    public Emprestimo registrarEmprestimo(Long livroId, Long usuarioId) {
        String url = catalogoServiceUrl + "/" + livroId;
        Livro livro = restTemplate.getForObject(url, Livro.class);

        if (livro != null && livro.isDisponivel()) {
            Emprestimo emprestimo = new Emprestimo();
            emprestimo.setLivroId(livroId);
            emprestimo.setUsuarioId(usuarioId);
            emprestimo.setEmprestimoData(LocalDate.now());
            Emprestimo salvaremprestimo = emprestimoRepositorio.save(emprestimo);

            // Notificar o CatalogService sobre a mudança de status do livro
            livro.setDisponivel(false);
            restTemplate.put(url, livro);

            return salvaremprestimo;

        } else {
            throw new IllegalStateException("Livro não disponível para empréstimo");
        }
    }

    public Emprestimo retornarEmprestimo(Long emprestimoId) {
        Emprestimo emprestimo = emprestimoRepositorio.findById(emprestimoId).orElseThrow(() -> new ResourceNotFoundException("Loan not found"));
        emprestimo.setDevolucaoData(LocalDate.now());
        emprestimo atualizarEmprestimo = emprestimoRepositorio.save(emprestimo);

        // Notificar o CatalogService sobre a mudança de status do livro
        String url = catalogoServiceUrl + "/" + emprestimo.getLivroId();
        Livro livro = restTemplate.getForObject(url, Livro.class);
        if (livro != null) {
            livro.setDisponivel(true);
            restTemplate.put(url, livro);
        }

        return atualizarEmprestimo;
    }
}
