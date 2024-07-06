package br.jus.tjba.api.push.publicador.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("notificador")
public interface NotificadorClient {

    @RequestMapping(method = RequestMethod.GET, value = "/notificar/teste")
    String notificar();
}
