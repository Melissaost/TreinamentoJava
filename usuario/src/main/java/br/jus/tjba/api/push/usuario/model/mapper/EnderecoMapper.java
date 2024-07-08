package br.jus.tjba.api.push.usuario.model.mapper;

import br.jus.tjba.api.push.usuario.model.Endereco;
import br.jus.tjba.api.push.usuario.dto.EnderecoDTO;

import java.util.Optional;

public interface EnderecoMapper {

    static Endereco mapEnderecoDtoToEndereco(EnderecoDTO dto) {
        return Endereco.builder()
                .rua(dto.rua())
                .uf(dto.uf())
                .cidade(dto.cidade())
                .bairro(dto.bairro())
                .cep(dto.cep())
                .numero(dto.numero())
                .build();
    }

    static Endereco updateEnderecoFromDto(Endereco enderecoExistente, EnderecoDTO dto) {
        Optional.ofNullable(dto.rua()).ifPresent(enderecoExistente::setRua);
        Optional.ofNullable(dto.uf()).ifPresent(enderecoExistente::setUf);
        Optional.ofNullable(dto.cidade()).ifPresent(enderecoExistente::setCidade);
        Optional.ofNullable(dto.bairro()).ifPresent(enderecoExistente::setBairro);
        Optional.ofNullable(dto.cep()).ifPresent(enderecoExistente::setCep);
        Optional.ofNullable(dto.numero()).ifPresent(enderecoExistente::setNumero);

        return enderecoExistente;
    }
}
