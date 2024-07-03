package br.jus.tjba.api.push.usuario.dto;

import br.jus.tjba.api.push.usuario.model.UsuarioProcessoSistema;
import br.jus.tjba.api.push.usuario.model.enums.SistemaEnum;


public record UsuarioProcessoSistemaDTO(
        Long usuarioId,
        SistemaEnum sistema,
        String numeroProcesso
) {
    public UsuarioProcessoSistemaDTO(UsuarioProcessoSistema usuarioProcessoSistema){
        this(usuarioProcessoSistema.getUsuario().getIdUsuario(),
                SistemaEnum.valueOf(usuarioProcessoSistema.getSistema().getSigla()),
                usuarioProcessoSistema.getNumeroProcesso());
    }
}
