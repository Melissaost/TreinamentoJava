package br.jus.tjba.api.push.publicador.controller;

import br.jus.tjba.api.push.publicador.dto.UsuarioSistemaDTO;
import br.jus.tjba.api.push.publicador.model.MensagemPendente;
import br.jus.tjba.api.push.publicador.service.MensagemPendenteService;
import br.jus.tjba.api.push.publicador.service.PublicacaoService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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

    @GetMapping
    public List<MensagemPendente> findAll() {
        return mensagemPendenteService.findAll();
    }

    @PostMapping("/save")
    public MensagemPendente save(@RequestBody MensagemPendente mensagemPendente) {
        return mensagemPendenteService.save(mensagemPendente);
    }

    @PostMapping("/sinalizar")
    @CircuitBreaker(name = "sinalizarUsuario", fallbackMethod = "sinalizarPendenteFallback")
    public ResponseEntity<String> sinalizar(@RequestParam String siglaSistema, @RequestParam String numeroProcesso, @RequestParam Long idUsuarioSistema) {
        publicacaoService.publicarMensagem(siglaSistema, numeroProcesso);
        return ResponseEntity.ok("Sinalizou");
    }

    public ResponseEntity<String> sinalizarPendenteFallback(String siglaSistema, String numeroProcesso, @RequestParam Long idUsuarioSistema, Exception ex) {
        publicacaoService.mensagemPendente(numeroProcesso, idUsuarioSistema);
        return ResponseEntity.ok("Pendente");
    }
}
