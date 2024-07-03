package br.jus.tjba.api.push.usuario.model.mapper;

import br.jus.tjba.api.push.usuario.model.Endereco;
import br.jus.tjba.api.push.usuario.dto.EnderecoDTO;

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
}
