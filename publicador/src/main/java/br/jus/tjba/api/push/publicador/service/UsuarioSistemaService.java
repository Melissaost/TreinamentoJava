package br.jus.tjba.api.push.publicador.service;

import br.jus.tjba.api.push.publicador.dto.CriarUsuarioSistemaDTO;
import br.jus.tjba.api.push.publicador.model.UsuarioSistema;
import br.jus.tjba.api.push.publicador.model.mapper.UsuarioSistemaMapper;
import br.jus.tjba.api.push.publicador.repository.UsuarioSistemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioSistemaService {

    @Autowired
    private UsuarioSistemaRepository usuarioSistemaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UsuarioSistema> findAll() {
        return usuarioSistemaRepository.findAll();
    }

    public UsuarioSistema save(CriarUsuarioSistemaDTO usuarioSistemadto) {
        if (usuarioSistemaRepository.existsByLogin(usuarioSistemadto.login())) {
            throw new DataIntegrityViolationException("Data integrity violation");
        }
        UsuarioSistema usuario = UsuarioSistemaMapper.mapCriarUsuarioSitemaDTOToUsuario(usuarioSistemadto, passwordEncoder);
        return usuarioSistemaRepository.save(usuario);
    }
}
