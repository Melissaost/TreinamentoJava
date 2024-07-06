package br.jus.tjba.api.push.notificador.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notificar")
public class NotificarController {

    @GetMapping("/teste")
    public String teste(){
        return "Ola mundo.";
    }
}
