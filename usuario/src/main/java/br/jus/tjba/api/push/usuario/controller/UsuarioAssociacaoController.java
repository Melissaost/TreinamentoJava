package br.jus.tjba.api.push.usuario.controller;

import br.jus.tjba.api.push.usuario.model.UsuarioProcessoSistema;
import br.jus.tjba.api.push.usuario.model.dto.UsuarioProcessoSistemaDTO;
import br.jus.tjba.api.push.usuario.service.UsuarioProcessoSistemaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario-associacao")
@SecurityRequirement(name = "bearer-key")
public class UsuarioAssociacaoController {

    @Autowired
    private UsuarioProcessoSistemaService usuarioProcessoSistemaService;

    @PostMapping("/associar")
    public ResponseEntity<UsuarioProcessoSistema> associarUsuarioAoSistema(@RequestBody UsuarioProcessoSistemaDTO dto) {
        UsuarioProcessoSistema associado = usuarioProcessoSistemaService.associarUsuarioAoSistema(dto);
        return ResponseEntity.ok(associado);
    }

    @DeleteMapping("/desassociar-processo/{id}")
    @Transactional
    public ResponseEntity<?> desassociarProcesso(@PathVariable Long id) {
        usuarioProcessoSistemaService.desassociarProcesso(id);
        return ResponseEntity.noContent().build();
    }
}
