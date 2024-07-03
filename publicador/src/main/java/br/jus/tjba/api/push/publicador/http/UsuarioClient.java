package br.jus.tjba.api.push.publicador.http;

import br.jus.tjba.api.push.publicador.dto.RequestUsuarioSistema;
import br.jus.tjba.api.push.publicador.dto.UsuarioSistemaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("usuario")
public interface UsuarioClient {

    @RequestMapping(method = RequestMethod.POST, value = "/api/usuario/lista-usuario-sistema")
    List<UsuarioSistemaDTO> getUsuarios(@RequestBody RequestUsuarioSistema requestUsuarioSistema);
}
