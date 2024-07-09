package br.jus.tjba.api.push.usuario.service;

import br.jus.tjba.api.push.usuario.dto.*;
import br.jus.tjba.api.push.usuario.model.Usuario;
import br.jus.tjba.api.push.usuario.model.mapper.UsuarioMapper;
import br.jus.tjba.api.push.usuario.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        if (usuarioRepository.existsByLogin(usuarioDTO.login())) {
            throw new DataIntegrityViolationException("O login '" + usuarioDTO.login() + "' já está em uso. Por favor, escolha outro login.");
        }
        Usuario usuario = UsuarioMapper.mapUsuarioDTOToUsuario(usuarioDTO, passwordEncoder);
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizar(Long id, UsuarioDTOAtualizacao usuarioDTO) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário sistema não encontrado com o id: " + id));
        UsuarioMapper.updateUsuarioFromDTO(usuarioExistente, usuarioDTO, passwordEncoder);
        return usuarioRepository.save(usuarioExistente);
    }

    public List<UsuarioSistemaDTO> getAllUsuariosSistema(String siglaSistema, String numeroProcesso) {
        List<Usuario> usuarioList = usuarioRepository.findBySistemaAndProcesso(siglaSistema, numeroProcesso);
        return usuarioList.stream().map(UsuarioSistemaDTO::new).toList();
    }

    @Transactional
    public boolean deleteUsuarioAndAssociations(Long idUsuario) {
        if (usuarioRepository.existsById(idUsuario)) {
            usuarioRepository.deleteById(idUsuario);
            return true;
        } else {
            return false;
        }
    }
}
