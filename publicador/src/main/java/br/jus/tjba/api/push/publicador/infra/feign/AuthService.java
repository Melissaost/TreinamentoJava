package br.jus.tjba.api.push.publicador.infra.feign;

import br.jus.tjba.api.push.publicador.dto.DadosAutenticacao;
import br.jus.tjba.api.push.publicador.dto.DadosTokenJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Component
public class AuthService {
    private final RestTemplate restTemplate;

    @Value("${ms.usuario.auth.url}")
    private String usuarioServiceUrl;

    @Value("${ms.usuario.auth.login}")
    private String login;

    @Value("${ms.usuario.auth.senha}")
    private String senha;

    public AuthService() {
        this.restTemplate = new RestTemplate();
    }

    private String token;
    private LocalDateTime tokenExpiryTime;

    public synchronized String obterToken() {
        if (token == null || tokenExpiryTime.isBefore(LocalDateTime.now())) {
            renovarToken();
        }
        return token;
    }

    private void renovarToken() {
        String loginUrl = usuarioServiceUrl + "/login";
        DadosAutenticacao dados = new DadosAutenticacao(login, senha);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<DadosAutenticacao> request = new HttpEntity<>(dados, headers);
        ResponseEntity<DadosTokenJWT> response = restTemplate.exchange(loginUrl, HttpMethod.POST, request, DadosTokenJWT.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            token = response.getBody().token();
            tokenExpiryTime = LocalDateTime.now().plusHours(2);
        } else {
            throw new RuntimeException("Falha ao obter o token de autenticação");
        }
    }

}
