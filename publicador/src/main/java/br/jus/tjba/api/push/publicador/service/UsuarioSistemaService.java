package br.jus.tjba.api.push.publicador.service;

import br.jus.tjba.api.push.publicador.model.UsuarioSistema;
import br.jus.tjba.api.push.publicador.repository.UsuarioSistemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioSistemaService {

    @Autowired
    private UsuarioSistemaRepository usuarioSistemaRepository;

    public List<UsuarioSistema> findAll() {
        return usuarioSistemaRepository.findAll();
    }

    public UsuarioSistema save(UsuarioSistema usuarioSistema) {
        if (usuarioSistemaRepository.existsByLogin(usuarioSistema.getLogin())) {
            throw new DataIntegrityViolationException("O login '" + usuarioSistema.getLogin() + "' já está em uso. Por favor, escolha outro login.");
        }
        return usuarioSistemaRepository.save(usuarioSistema);
    }
}
