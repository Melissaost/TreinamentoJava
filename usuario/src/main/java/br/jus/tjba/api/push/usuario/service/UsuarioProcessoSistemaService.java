package br.jus.tjba.api.push.usuario.service;

import br.jus.tjba.api.push.usuario.model.Sistema;
import br.jus.tjba.api.push.usuario.model.Usuario;
import br.jus.tjba.api.push.usuario.model.UsuarioProcessoSistema;
import br.jus.tjba.api.push.usuario.dto.UsuarioProcessoSistemaDTO;
import br.jus.tjba.api.push.usuario.repository.SistemaRepository;
import br.jus.tjba.api.push.usuario.repository.UsuarioProcessoSistemaRepository;
import br.jus.tjba.api.push.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioProcessoSistemaService {

    @Autowired
    private UsuarioProcessoSistemaRepository usuarioProcessoSistemaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SistemaRepository sistemaRepository;

    @Transactional
    public UsuarioProcessoSistema associarUsuarioAoSistema(UsuarioProcessoSistemaDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com ID: " + dto.usuarioId()));

        Sistema sistema = sistemaRepository.findBySigla(dto.sistema().name())
                .orElseThrow(() -> new IllegalArgumentException("Sistema não encontrado com ID: " + dto.sistema()));

        UsuarioProcessoSistema ups = new UsuarioProcessoSistema();
        ups.setUsuario(usuario);
        ups.setSistema(sistema);
        ups.setNumeroProcesso(dto.numeroProcesso());

        usuarioProcessoSistemaRepository.save(ups);
        return ups;
    }

    @Transactional
    public String desassociarProcesso(Long idUsuario, String numProcesso, String siglaSistema) {
        UsuarioProcessoSistema usuarioProcessoSistema = usuarioProcessoSistemaRepository.findByUsuarioProcessoSistema(idUsuario, numProcesso, siglaSistema);
        if(usuarioProcessoSistema != null) {
            usuarioProcessoSistemaRepository.delete(usuarioProcessoSistema);
            return "Removido com sucesso.";
        }else {
            return "Associação não encontrada.";
        }
    }
}