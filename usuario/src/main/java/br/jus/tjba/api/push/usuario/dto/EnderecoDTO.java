package br.jus.tjba.api.push.usuario.dto;

import br.jus.tjba.api.push.usuario.model.Endereco;
import br.jus.tjba.api.push.usuario.model.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.stream.Collectors;

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
        public EnderecoDTO(Endereco endereco) {
                this(endereco.getRua(), endereco.getBairro(), endereco.getNumero(), endereco.getCidade(), endereco.getUf(), endereco.getCep());
        }
}