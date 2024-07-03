package br.jus.tjba.api.push.usuario.dto;

import br.jus.tjba.api.push.usuario.model.Usuario;

public record UsuarioSistemaDTO(
        String login,
        String nome
) {
    public UsuarioSistemaDTO(Usuario usuario) {
        this(usuario.getLogin(),
                usuario.getNome());
    }
}
