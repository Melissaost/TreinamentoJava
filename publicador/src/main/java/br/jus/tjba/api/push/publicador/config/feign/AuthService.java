package br.jus.tjba.api.push.publicador.config.feign;

import br.jus.tjba.api.push.publicador.dto.DadosAutenticacao;
import br.jus.tjba.api.push.publicador.dto.DadosTokenJWT;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthService {
    private final RestTemplate restTemplate;

    public AuthService() {
        this.restTemplate = new RestTemplate();
    }

    public String obterToken() {
        String url = "http://localhost:8082/usuario/login";

        // Cria o objeto de dados de autenticação
        DadosAutenticacao dados = new DadosAutenticacao("melissa-ost@hotmail.com", "senha1");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<DadosAutenticacao> request = new HttpEntity<>(dados, headers);
        ResponseEntity<DadosTokenJWT> response = restTemplate.exchange(url, HttpMethod.POST, request, DadosTokenJWT.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody().token();
        }

        throw new RuntimeException("Falha ao obter o token de autenticação");
    }

}
