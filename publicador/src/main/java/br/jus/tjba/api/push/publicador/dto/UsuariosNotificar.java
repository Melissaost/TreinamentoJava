package br.jus.tjba.api.push.publicador.dto;


public record UsuariosNotificar(
        UsuarioSistemaDTO usuarioSistemaDTO,
        String siglaSistema,
        String numeroProcesso,
        String mensagem
) {

    public UsuariosNotificar(UsuarioSistemaDTO usuarioSistemaDTO, String siglaSistema, String numeroProcesso, String mensagem) {
        this.usuarioSistemaDTO = usuarioSistemaDTO;
        this.siglaSistema = siglaSistema;
        this.numeroProcesso = numeroProcesso;
        this.mensagem = mensagem;
    }
}
