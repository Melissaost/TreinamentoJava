package br.jus.tjba.api.push.usuario.model.mapper;

import br.jus.tjba.api.push.usuario.model.Usuario;
import br.jus.tjba.api.push.usuario.model.dto.UsuarioDTO;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UsuarioMapper {

    static Usuario mapUsuarioDTOToUsuario(UsuarioDTO usuarioDTO, PasswordEncoder passwordEncoder) {
        String senhaCriptografada = passwordEncoder.encode(usuarioDTO.senha());

        return Usuario.builder()
                .nome(usuarioDTO.nome())
                .cpf(usuarioDTO.cpf())
                .senha(senhaCriptografada)
                .login(usuarioDTO.login())
                .endereco(EnderecoMapper.mapEnderecoDtoToEndereco(usuarioDTO.endereco()))
                .build();
    }
}
