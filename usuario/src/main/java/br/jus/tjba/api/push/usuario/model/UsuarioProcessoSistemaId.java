package br.jus.tjba.api.push.usuario.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UsuarioProcessoSistemaId implements Serializable {

    private Long usuario;
    private Long sistema;
}
