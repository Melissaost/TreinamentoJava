package br.jus.tjba.api.push.usuario.model.dto;

import br.jus.tjba.api.push.usuario.model.enums.SistemaEnum;


public record UsuarioProcessoSistemaDTO(
        Long usuarioId,
        SistemaEnum sistema,
        String numeroProcesso
) {
}
