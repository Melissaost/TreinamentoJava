package br.jus.tjba.api.push.usuario.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UsuarioDTO(
        @NotBlank
        @Email
        String login,

        @NotBlank
        String senha,

        @NotBlank
        String nome,

        @NotBlank
        @Pattern(regexp = "\\d{11,14}")
        String cpf,

        @Valid
        EnderecoDTO endereco) {
}
