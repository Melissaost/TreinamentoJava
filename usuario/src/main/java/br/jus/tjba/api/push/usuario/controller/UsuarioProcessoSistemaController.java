package br.jus.tjba.api.push.usuario.controller;

import br.jus.tjba.api.push.usuario.model.Usuario;
import br.jus.tjba.api.push.usuario.model.UsuarioProcessoSistema;
import br.jus.tjba.api.push.usuario.model.dto.UsuarioProcessoSistemaDTO;
import br.jus.tjba.api.push.usuario.service.UsuarioProcessoSistemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario-processo-sistema")
public class UsuarioProcessoSistemaController {

    @Autowired
    private UsuarioProcessoSistemaService usuarioProcessoSistemaService;

    @PostMapping("/associar")
    public ResponseEntity<UsuarioProcessoSistema> associarUsuarioAoSistema(@RequestBody UsuarioProcessoSistemaDTO dto) {
        UsuarioProcessoSistema associado = usuarioProcessoSistemaService.associarUsuarioAoSistema(dto);
        return ResponseEntity.ok(associado);
    }
}
