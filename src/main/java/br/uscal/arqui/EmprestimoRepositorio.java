package br.uscal.arqui;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmprestimoRepositorio extends JpaRepository<Emprestimo, Long> {
    List<Emprestimo> obterUsuarioId(Long usuarioId);
    List<Emprestimo> obterPorLivroIdRetornandoDataNull(Long livroId);
}
