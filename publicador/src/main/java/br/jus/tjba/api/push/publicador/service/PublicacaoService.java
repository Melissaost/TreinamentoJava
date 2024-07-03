package br.jus.tjba.api.push.publicador.service;

import br.jus.tjba.api.push.publicador.dto.RequestUsuarioSistema;
import br.jus.tjba.api.push.publicador.dto.UsuarioSistemaDTO;
import br.jus.tjba.api.push.publicador.http.UsuarioClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicacaoService {

    private final UsuarioClient usuarioClient;

    public PublicacaoService(UsuarioClient usuarioClient) {
        this.usuarioClient = usuarioClient;
    }

    public List<UsuarioSistemaDTO> publicarMensagem(String siglaSistema, String numeroProcesso, String mensagem) {
        RequestUsuarioSistema requestUsuarioSistema = new RequestUsuarioSistema(siglaSistema, numeroProcesso);
        List<UsuarioSistemaDTO> usuarios = usuarioClient.getUsuarios(requestUsuarioSistema);
        return usuarios;
    }
}
