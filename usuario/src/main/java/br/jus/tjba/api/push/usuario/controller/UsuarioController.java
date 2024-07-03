package br.jus.tjba.api.push.usuario.controller;

import br.jus.tjba.api.push.usuario.dto.*;
import br.jus.tjba.api.push.usuario.model.Usuario;
import br.jus.tjba.api.push.usuario.service.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("teste")
    public String teste(){
        return "Ola mundo.";
    }

    @GetMapping("/listaPaginada")
    public ResponseEntity<Page<UsuarioResponse>> getAllPageUsuarios(@RequestBody PageableSearchUsuarios request){
        Page<UsuarioResponse> usuariosList = usuarioService.findAllPageUsuarios(request);
        return ResponseEntity.ok(usuariosList);
    }

    @GetMapping("/lista-usuario-sistema")
    public ResponseEntity<List<UsuarioSistemaDTO>> getAllUsuariosSistema(@RequestBody RequestUsuarioSistema request){
        List<UsuarioSistemaDTO> usuariosList = usuarioService.getAllUsuariosSistema(request);
        return ResponseEntity.ok(usuariosList);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findId(@PathVariable Long id){
        Usuario usuario = usuarioService.findById(id);
        return ResponseEntity.ok(new UsuarioResponse(usuario));
    }

    @PostMapping("/")
    public ResponseEntity<?> cadastrarUsuario(@RequestBody @Valid UsuarioDTO usuario, UriComponentsBuilder uriBuilder) {
        Usuario usuarioSalvo = usuarioService.save(usuario);
        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuarioSalvo.getIdUsuario()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioResponse(usuarioSalvo));
    }
}
