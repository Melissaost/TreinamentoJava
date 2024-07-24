package br.jus.tjba.api.push.usuario.model.mapper;

import br.jus.tjba.api.push.usuario.model.Endereco;
import br.jus.tjba.api.push.usuario.dto.EnderecoDTO;
import br.jus.tjba.api.push.usuario.util.MapperUtils;

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
        MapperUtils.setIfNotEmpty(enderecoExistente::setRua, dto.rua());
        MapperUtils.setIfNotEmpty(enderecoExistente::setBairro, dto.bairro());
        MapperUtils.setIfNotEmpty(enderecoExistente::setNumero, dto.numero());
        MapperUtils.setIfNotEmpty(enderecoExistente::setCidade, dto.cidade());
        MapperUtils.setIfNotEmpty(enderecoExistente::setUf, dto.uf());
        MapperUtils.setIfNotEmpty(enderecoExistente::setCep, dto.cep());

        return enderecoExistente;
    }
}
