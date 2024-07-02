package br.jus.tjba.api.push.usuario.service;

import br.jus.tjba.api.push.usuario.model.Usuario;
import br.jus.tjba.api.push.usuario.model.dto.UsuarioDTO;
import br.jus.tjba.api.push.usuario.model.mapper.UsuarioMapper;
import br.jus.tjba.api.push.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario save(UsuarioDTO usuarioDTO) {
        Usuario usuario = UsuarioMapper.mapUsuarioDTOToUsuario(usuarioDTO);
        return usuarioRepository.save(usuario);
    }

    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }
}
