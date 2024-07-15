package br.jus.tjba.api.push.publicador.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CriarUsuarioSistemaDTO(
        @NotBlank
        String login,

        @NotBlank
        String chaveAcesso
) {
}
