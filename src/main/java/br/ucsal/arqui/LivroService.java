package br.ucsal.arqui;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class LivroService {
    @Autowired
    private LivroRepositorio livroRepositorio;

    public List<Livro> obterLivros() {
        return livroRepositorio.findAll();
    }

    public Optional<Livro> getLivroById(Long id) {
        return livroRepositorio.findById(id);
    }

    public Livro adicionarLivro(Livro livro) {
        return livroRepositorio.save(livro);
    }

    public Livro atualizarLivro(Long id, Livro detalhesLivro) {
        Livro livro = livroRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado"));
        livro.setTitulo(detalhesLivro.getTitulo());
        livro.setAutor(detalhesLivro.getAutor());
        livro.setIsbn(detalhesLivro.getIsbn());
        livro.setDisponivel(detalhesLivro.isDisponivel());
        return livroRepositorio.save(livro);
    }

    public void deletarLivro(Long id) {
        Livro livro = livroRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado"));
        livroRepositorio.delete(livro);
    }

    public Livro atualizarStatus(Long id, boolean available) {
        Livro livro = livroRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado"));
        livro.setDisponivel(available);
        return livroRepositorio.save(livro);
    }
}