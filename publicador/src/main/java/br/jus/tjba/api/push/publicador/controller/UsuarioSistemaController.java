package br.jus.tjba.api.push.publicador.controller;

import br.jus.tjba.api.push.publicador.dto.CriarUsuarioSistemaDTO;
import br.jus.tjba.api.push.publicador.dto.UsuarioSistemaDTO;
import br.jus.tjba.api.push.publicador.model.UsuarioSistema;
import br.jus.tjba.api.push.publicador.service.UsuarioSistemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario-sistema")
public class UsuarioSistemaController {

    @Autowired
    private UsuarioSistemaService usuarioSistemaService;

    @GetMapping
    public List<UsuarioSistema> findAll() {
        return usuarioSistemaService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody CriarUsuarioSistemaDTO usuarioSistema) {
        UsuarioSistema usuarioCriado = usuarioSistemaService.save(usuarioSistema);
        return ResponseEntity.ok(usuarioCriado.getLogin());
    }
}
