package br.jus.tjba.api.push.publicador.model.mapper;

import br.jus.tjba.api.push.publicador.dto.CriarUsuarioSistemaDTO;
import br.jus.tjba.api.push.publicador.model.UsuarioSistema;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UsuarioSistemaMapper {

    static UsuarioSistema mapCriarUsuarioSitemaDTOToUsuario(CriarUsuarioSistemaDTO usuarioDTO, PasswordEncoder passwordEncoder) {
        String senhaCriptografada = passwordEncoder.encode(usuarioDTO.chaveAcesso());

        return UsuarioSistema.builder()
                .chaveAcesso(senhaCriptografada)
                .login(usuarioDTO.login())
                .build();
    }
}
