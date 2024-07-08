package br.jus.tjba.api.push.usuario.controller;

import br.jus.tjba.api.push.usuario.dto.UsuarioResponse;
import br.jus.tjba.api.push.usuario.model.UsuarioProcessoSistema;
import br.jus.tjba.api.push.usuario.dto.UsuarioProcessoSistemaDTO;
import br.jus.tjba.api.push.usuario.service.UsuarioProcessoSistemaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario-processo")
@SecurityRequirement(name = "bearer-key")
public class UsuarioAssociacaoController {

    @Autowired
    private UsuarioProcessoSistemaService usuarioProcessoSistemaService;

    @PostMapping("/associar")
    public ResponseEntity<UsuarioResponse> associarUsuarioAoSistema(@RequestBody UsuarioProcessoSistemaDTO dto) {
        UsuarioProcessoSistema associado = usuarioProcessoSistemaService.associarUsuarioAoSistema(dto);
        return ResponseEntity.ok(new UsuarioResponse(associado.getUsuario()));
    }

    @DeleteMapping("/desassociar-processo/")
    public ResponseEntity<?> desassociarProcesso(Long idUsuario, String numProcesso, String siglaSistema) {
        String delete = usuarioProcessoSistemaService.desassociarProcesso(idUsuario, numProcesso, siglaSistema);
        return ResponseEntity.noContent().build();
    }
}
