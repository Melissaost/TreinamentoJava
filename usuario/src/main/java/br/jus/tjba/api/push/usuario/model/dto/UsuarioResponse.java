package br.jus.tjba.api.push.usuario.model.dto;

import br.jus.tjba.api.push.usuario.model.Endereco;
import br.jus.tjba.api.push.usuario.model.Usuario;

public record UsuarioResponse(
        String login,
        String nome,
        String cpf,
        Endereco endereco)  {

    public UsuarioResponse(Usuario usuario){
        this(usuario.getLogin(),
                usuario.getNome(),
                usuario.getCpf(),
                usuario.getEndereco());
    }
}