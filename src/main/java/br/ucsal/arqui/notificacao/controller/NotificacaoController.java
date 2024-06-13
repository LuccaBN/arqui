package br.ucsal.arqui.notificacao.controller;

import br.ucsal.arqui.notificacao.service.NotificacaoService;
import br.ucsal.arqui.notificacao.model.Notificacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificacao")
public class NotificacaoController {
    @Autowired
    private NotificacaoService notificacaoService;

    @GetMapping
    public List<Notificacao> obterTodasNotificacoes() {
        return notificacaoService.obterTodasNotificacoes();
    }

    @GetMapping("/{usuarioId}")
    public List<Notificacao> obterNotificacaoById(@PathVariable Long Id) {
        return (List<Notificacao>) notificacaoService.obterNotificacaoById(Id);
    }

    @PostMapping
    public Notificacao criarNotificacao(@RequestBody Notificacao notificacao) {
        return notificacaoService.criarNotificacao(
                notificacao.getUsuarioId(),
                notificacao.getEmail(),
                notificacao.getAssunto(),
                notificacao.getMensagem());
    }

}

