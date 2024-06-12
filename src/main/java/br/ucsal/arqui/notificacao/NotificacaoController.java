package br.ucsal.arqui.notificacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificacaoController {
    @Autowired
    private NotificacaoService notificacaoService;

    @GetMapping("/{usuarioId}")
    public List<Notificacao> getNotificationsByUserId(@PathVariable Long usuarioId) {
        return notificacaoService.getNotificacaoByUserId(usuarioId);
    }

    @PostMapping("/email")
    public ResponseEntity<Notificacao> mandarEmail(@RequestParam Long usuarioId, @RequestParam String para,
                                                              @RequestParam String assunto, @RequestParam String mensagem) {
        Notificacao notificacao = notificacaoService.mandarEmail(usuarioId,para,assunto,mensagem);
        return ResponseEntity.ok(notificacao);
    }

    @PostMapping("/lembrar")
    public ResponseEntity<Void> lembrarDevolucao(@RequestParam Long usuarioId, @RequestParam String para) {
        notificacaoService.devolucao(usuarioId, para);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/atrasado")
    public ResponseEntity<Void> atrasoDevolucao(@RequestParam Long usuarioId, @RequestParam String para) {
        notificacaoService.atraso(usuarioId, para);
        return ResponseEntity.noContent().build();
    }
}

