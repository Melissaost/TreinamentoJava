package br.jus.tjba.api.push.usuario.controller;

import br.jus.tjba.api.push.usuario.model.Usuario;
import br.jus.tjba.api.push.usuario.model.dto.UsuarioDTO;
import br.jus.tjba.api.push.usuario.model.dto.UsuarioResponse;
import br.jus.tjba.api.push.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("teste")
    public String teste(){
        return "Ola mundo.";
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
