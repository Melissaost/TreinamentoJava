package br.jus.tjba.api.push.usuario.model.dto;

import br.jus.tjba.api.push.usuario.model.Usuario;
import java.util.List;
import java.util.stream.Collectors;

public record UsuarioResponse(
        String login,
        String nome,
        String cpf,
        EnderecoDTO endereco,
        List<UsuarioProcessoSistemaDTO> usuarioProcessoSistema)  {

    public UsuarioResponse(Usuario usuario) {
        this(usuario.getLogin(),
                usuario.getNome(),
                usuario.getCpf(),
                new EnderecoDTO(usuario.getEndereco()),
                usuario.getSistemas().stream()
                        .map(UsuarioProcessoSistemaDTO::new)
                        .collect(Collectors.toList()));
    }
}