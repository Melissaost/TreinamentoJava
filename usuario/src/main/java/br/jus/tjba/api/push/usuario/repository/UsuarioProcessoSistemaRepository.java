package br.jus.tjba.api.push.usuario.repository;

import br.jus.tjba.api.push.usuario.model.UsuarioProcessoSistema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioProcessoSistemaRepository extends JpaRepository<UsuarioProcessoSistema, Long> {

    @Query(value = "SELECT ups.* " +
            "FROM USUARIO_PROCESSO_SISTEMA ups " +
            "INNER JOIN SISTEMA s ON ups.ID_SISTEMA = s.ID_SISTEMA " +
            "WHERE ups.ID_USUARIO = :idUsuario " +
            "AND s.SIGLA = :siglaSistema " +
            "AND ups.NUMEROPROCESSO = :numProcesso ", nativeQuery = true)
    UsuarioProcessoSistema findByUsuarioProcessoSistema(Long idUsuario, String numProcesso, String siglaSistema);
}
