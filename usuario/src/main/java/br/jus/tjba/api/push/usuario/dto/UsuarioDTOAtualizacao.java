package br.jus.tjba.api.push.usuario.dto;

import jakarta.validation.Valid;

public record UsuarioDTOAtualizacao(
        String senha,
        String nome,
        @Valid
        EnderecoDTO endereco
) {
}
