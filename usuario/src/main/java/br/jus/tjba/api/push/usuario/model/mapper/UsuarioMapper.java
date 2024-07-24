package br.jus.tjba.api.push.usuario.model.mapper;

import br.jus.tjba.api.push.usuario.dto.UsuarioDTOAtualizacao;
import br.jus.tjba.api.push.usuario.model.Usuario;
import br.jus.tjba.api.push.usuario.dto.UsuarioDTO;
import br.jus.tjba.api.push.usuario.util.MapperUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

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

    static void updateUsuarioFromDTO(Usuario usuarioExistente, UsuarioDTOAtualizacao usuarioDTO, PasswordEncoder passwordEncoder) {
        MapperUtils.setIfNotEmpty(usuarioExistente::setNome, usuarioDTO.nome());
        usuarioExistente.setEndereco(EnderecoMapper.updateEnderecoFromDto(usuarioExistente.getEndereco(), usuarioDTO.endereco()));
        if (!usuarioDTO.senha().trim().isEmpty()) {
            String senhaCriptografada = passwordEncoder.encode(usuarioDTO.senha());
            usuarioExistente.setSenha(senhaCriptografada);
        }
    }
}
