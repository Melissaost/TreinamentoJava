package br.jus.tjba.api.push.publicador.controller;

import br.jus.tjba.api.push.publicador.model.UsuarioSistema;
import br.jus.tjba.api.push.publicador.service.UsuarioSistemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario-sistema")
public class UsuarioSistemaController {

    @Autowired
    private UsuarioSistemaService usuarioSistemaService;

    @GetMapping
    public List<UsuarioSistema> findAll() {
        return usuarioSistemaService.findAll();
    }

    @PostMapping
    public UsuarioSistema save(@RequestBody UsuarioSistema usuarioSistema) {
        return usuarioSistemaService.save(usuarioSistema);
    }
}
