package br.ucsal.arqui.notificacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificacaoService {
    @Autowired
    private NotificacaoRepositorio notificacaoRepositorio;

    @Autowired
    private JavaMailSender mailSender;

    public Notificacao mandarEmail(Long usuarioId, String para, String assunto, String mensagem) {
        // Enviar e-mail
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(para);
        email.setSubject(assunto);
        email.setText(mensagem);
        mailSender.send(email);

        // Registrar notificação no banco de dados
        Notificacao notificacao = new Notificacao();
        notificacao.setUsuarioId(usuarioId);
        notificacao.setMensagem(mensagem);
        notificacao.setEmail(para);
        notificacao.setSentAt(LocalDateTime.now());
        return notificacaoRepositorio.save(notificacao);
    }

    public void devolucao(Long usuarioId, String email) {
        String assunto = "Lembrete de Devolução";
        String mensagem = "Este é um lembrete para devolver o livro emprestado.";
        mandarEmail(usuarioId, email, assunto, mensagem);
    }

    public void atraso(Long usuarioId, String email) {
        String assunto = "Notificação de Atraso na Devolução";
        String mensagem = "Você está atrasado na devolução de um livro. Por favor, devolva o mais rápido possível.";
        mandarEmail(usuarioId, email, assunto, mensagem);
    }

    public List<Notificacao> getNotificacaoByUserId(Long usuarioId) {
        return notificacaoRepositorio.findByUserId(usuarioId);
    }
}
