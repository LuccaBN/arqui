package br.ucsal.arqui.repositorios;
import br.ucsal.arqui.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepositorio extends JpaRepository<Livro, Long> {
        List<Livro> findByDisponivel(boolean disponivel);

}

