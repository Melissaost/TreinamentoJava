package br.jus.tjba.api.push.publicador.controller;

import br.jus.tjba.api.push.publicador.service.PublicacaoService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sinalizar")
public class SinalizarController {

    @Autowired
    private PublicacaoService publicacaoService;

    @PostMapping("/")
    @CircuitBreaker(name = "sinalizarUsuario", fallbackMethod = "sinalizarPendenteFallback")
    public ResponseEntity<String> sinalizar(@RequestParam String siglaSistema, @RequestParam String numeroProcesso) {
        publicacaoService.publicarMensagem(siglaSistema, numeroProcesso);
        return ResponseEntity.ok("Sinalizou");
    }

    public ResponseEntity<String> sinalizarPendenteFallback(String siglaSistema, String numeroProcesso, Exception ex) {
        publicacaoService.mensagemPendente(siglaSistema, numeroProcesso);
        return ResponseEntity.ok("Pendente");
    }
}
