package br.ucsal.arqui.notificacao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificacaoRepositorio extends JpaRepository<Notificacao, Long> {
    List<Notificacao> findByUserId(Long usuarioId);
}
