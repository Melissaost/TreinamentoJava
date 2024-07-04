package br.jus.tjba.api.push.publicador.service;

import br.jus.tjba.api.push.publicador.dto.UsuarioSistemaDTO;
import br.jus.tjba.api.push.publicador.http.UsuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicacaoService {

    @Autowired
    private final UsuarioClient usuarioClient;

    public PublicacaoService(UsuarioClient usuarioClient) {
        this.usuarioClient = usuarioClient;
    }

    public List<UsuarioSistemaDTO> publicarMensagem(String siglaSistema, String numeroProcesso) {
        List<UsuarioSistemaDTO> usuarios = usuarioClient.getUsuarios(siglaSistema, numeroProcesso).getBody();
        return usuarios;
    }
}
