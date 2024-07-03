package br.jus.tjba.api.push.usuario.service;

import br.jus.tjba.api.push.usuario.dto.*;
import br.jus.tjba.api.push.usuario.model.Usuario;
import br.jus.tjba.api.push.usuario.model.mapper.UsuarioMapper;
import br.jus.tjba.api.push.usuario.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<UsuarioResponse> findAllPageUsuarios(PageableSearchUsuarios request) {
        PageRequest pageRequest = PageRequest.of(request.page(), request.size());
        Page<Usuario> usuariosList = usuarioRepository.findAllPageUsuarios(pageRequest);

        List<UsuarioResponse> usuarioResponses = usuariosList.getContent().stream()
                .map(UsuarioResponse::new)
                .collect(Collectors.toList());

        return new PageImpl<>(usuarioResponses, pageRequest, usuariosList.getTotalElements());
    }

    public Usuario findById(Long id) {
        return usuarioRepository.getReferenceById(id);
    }

    public Usuario save(UsuarioDTO usuarioDTO) {
        Usuario usuario = UsuarioMapper.mapUsuarioDTOToUsuario(usuarioDTO, passwordEncoder);
        return usuarioRepository.save(usuario);
    }

    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    public List<UsuarioSistemaDTO> getAllUsuariosSistema(RequestUsuarioSistema request) {
        List<Usuario> usuarioList = usuarioRepository.findBySistemaAndProcesso(request.siglaSistema(), request.numeroProcesso());
        return usuarioList.stream().map(UsuarioSistemaDTO::new).toList();
    }
}
