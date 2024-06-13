package br.ucsal.arqui.notificacao.repositorio;

import br.ucsal.arqui.notificacao.model.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificacaoRepositorio extends JpaRepository<Notificacao, Long> {
    List<Notificacao> obterPorIdUsuario(Long usuarioId);
}
