package br.jus.tjba.api.push.publicador.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class testeController {

    @GetMapping("/teste")
    public String teste(){
        return "Ola mundo.";
    }
}
