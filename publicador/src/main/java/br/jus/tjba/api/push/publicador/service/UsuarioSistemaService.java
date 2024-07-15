package br.jus.tjba.api.push.publicador.service;

import br.jus.tjba.api.push.publicador.model.UsuarioSistema;
import br.jus.tjba.api.push.publicador.repository.UsuarioSistemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioSistemaService {

    @Autowired
    private UsuarioSistemaRepository usuarioSistemaRepository;

    public List<UsuarioSistema> findAll() {
        return usuarioSistemaRepository.findAll();
    }

    public UsuarioSistema save(UsuarioSistema usuarioSistema) {
        if (usuarioSistemaRepository.existsByLogin(usuarioSistema.getLogin())) {
            throw new DataIntegrityViolationException("Data integrity violation");
        }
        return usuarioSistemaRepository.save(usuarioSistema);
    }
}
