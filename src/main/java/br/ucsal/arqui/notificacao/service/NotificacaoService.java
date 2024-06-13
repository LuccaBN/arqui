package br.ucsal.arqui.notificacao.service;
import br.ucsal.arqui.notificacao.model.Notificacao;
import br.ucsal.arqui.notificacao.repositorio.NotificacaoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificacaoService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificacaoRepositorio notificacaoRepositorio;

    @Autowired
    private JavaMailSender mailSender;

    private final String catalogoServiceUrl = "http://localhost:8080/api/livros";

    public List<Notificacao> obterTodasNotificacoes() {
        return notificacaoRepositorio.findAll();
    }

    public Notificacao obterNotificacaoById(Long id) {
        return notificacaoRepositorio.findById(id).orElse(null);
    }

    public Notificacao criarNotificacao(Long usuarioId, String para, String assunto, String mensagem) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(para);
        email.setSubject(assunto);
        email.setText(mensagem);
        mailSender.send(email);

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
        criarNotificacao(usuarioId, email, assunto, mensagem);
    }

    public void atraso(Long usuarioId, String email) {
        String assunto = "Notificação de Atraso na Devolução";
        String mensagem = "Você está atrasado na devolução de um livro. Por favor, devolva o mais rápido possível.";
        criarNotificacao(usuarioId, email, assunto, mensagem);
    }

    public List<Notificacao> obterNotificacaoPorUsuario(Long usuarioId) {
        return notificacaoRepositorio.obterPorIdUsuario(usuarioId);
    }

    public Livro obterLivroPorId(Long livroId) {
        String url = catalogoServiceUrl + "/" + livroId;
        return restTemplate.getForObject(url, Livro.class);
    }

    public static class Livro {
        private Long id;
        private String titulo;
        private String autor;
        private String isbn;
        private String disponivel;

        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public String getTitulo() {
            return titulo;
        }
        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }
        public String getAutor() {
            return autor;
        }
        public void setAutor(String autor) {
            this.autor = autor;
        }
        public String getIsbn() {
            return isbn;
        }
        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }
        public String getDisponivel() {
            return disponivel;
        }
        public void setDisponivel(String disponivel) {
            this.disponivel = disponivel;
        }

    }
}