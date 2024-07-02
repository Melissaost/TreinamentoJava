package br.jus.tjba.api.push.usuario.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnderecoDTO(
        @NotBlank
        String rua,

        @NotBlank
        String bairro,

        String numero,

        @NotBlank
        String cidade,

        @NotBlank
        @Pattern(regexp = "[A-Z]{2}")
        String uf,

        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String cep
) {
}