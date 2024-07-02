package br.jus.tjba.api.push.usuario.model.mapper;

import br.jus.tjba.api.push.usuario.model.Usuario;
import br.jus.tjba.api.push.usuario.model.dto.UsuarioDTO;

public interface UsuarioMapper {

    static Usuario mapUsuarioDTOToUsuario(UsuarioDTO usuarioDTO) {
        return Usuario.builder()
                .nome(usuarioDTO.nome())
                .cpf(usuarioDTO.cpf())
                .senha(usuarioDTO.senha())
                .login(usuarioDTO.login())
                .endereco(EnderecoMapper.mapEnderecoDtoToEndereco(usuarioDTO.endereco()))
                .build();
    }
}
