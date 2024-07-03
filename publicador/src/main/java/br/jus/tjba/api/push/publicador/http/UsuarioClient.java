package br.jus.tjba.api.push.publicador.http;

import br.jus.tjba.api.push.publicador.dto.RequestUsuarioSistema;
import br.jus.tjba.api.push.publicador.dto.UsuarioSistemaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("usuario")
public interface UsuarioClient {

    @RequestMapping(method = RequestMethod.GET, value = "/usuarios/lista-usuario-sistema")
    ResponseEntity<List<UsuarioSistemaDTO>> getUsuarios(@RequestParam String siglaSistema, @RequestParam String numeroProcesso);
}
