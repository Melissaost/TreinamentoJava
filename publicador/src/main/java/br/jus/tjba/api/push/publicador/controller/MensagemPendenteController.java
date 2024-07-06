package br.jus.tjba.api.push.publicador.controller;

import br.jus.tjba.api.push.publicador.dto.UsuarioSistemaDTO;
import br.jus.tjba.api.push.publicador.model.MensagemPendente;
import br.jus.tjba.api.push.publicador.service.MensagemPendenteService;
import br.jus.tjba.api.push.publicador.service.PublicacaoService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mensagem-pendente")
public class MensagemPendenteController {

    @Autowired
    private MensagemPendenteService mensagemPendenteService;

    @Autowired
    private PublicacaoService publicacaoService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/all")
    public List<MensagemPendente> findAll() {
        return mensagemPendenteService.findAll();
    }

    @PostMapping("/save")
    public MensagemPendente save(@RequestBody MensagemPendente mensagemPendente) {
        return mensagemPendenteService.save(mensagemPendente);
    }

    @GetMapping("/notificar")
    public ResponseEntity<String> notificar() {
        return ResponseEntity.ok(publicacaoService.notificar());
    }

    @PostMapping("/sinalizar")
    @CircuitBreaker(name = "sinalizarUsuario", fallbackMethod = "sinalizarPendenteFallback")
    public ResponseEntity<String> sinalizar(@RequestParam String siglaSistema, @RequestParam String numeroProcesso, @RequestParam Long idUsuarioSistema) {
        publicacaoService.publicarMensagem(siglaSistema, numeroProcesso);

        Message msg = new Message(("Notificar usuários. ").getBytes());
        rabbitTemplate.send("usuarios.notificar", msg);

        return ResponseEntity.ok("Sinalizou");
    }

    public ResponseEntity<String> sinalizarPendenteFallback(String siglaSistema, String numeroProcesso, @RequestParam Long idUsuarioSistema, Exception ex) {
        publicacaoService.mensagemPendente(numeroProcesso, idUsuarioSistema);
        return ResponseEntity.ok("Pendente");
    }
}
