package br.jus.tjba.api.push.usuario.controller;

import br.jus.tjba.api.push.usuario.model.Usuario;
import br.jus.tjba.api.push.usuario.model.dto.UsuarioDTO;
import br.jus.tjba.api.push.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public Optional<Usuario> findId(@PathVariable Long id){
        return usuarioService.findById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody @Valid UsuarioDTO usuario) {
        Usuario usuarioSalvo = usuarioService.save(usuario);
        return ResponseEntity.ok(usuarioSalvo);
    }
}
