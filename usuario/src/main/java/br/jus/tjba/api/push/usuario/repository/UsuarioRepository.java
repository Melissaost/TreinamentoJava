package br.jus.tjba.api.push.usuario.repository;

import br.jus.tjba.api.push.usuario.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    UserDetails findByLogin(String login);

    @Query(value = "SELECT * FROM USUARIO ORDER BY NOME",
            countQuery = "SELECT count(*) FROM USUARIO", nativeQuery = true)
    Page<Usuario> findAllPageUsuarios(Pageable pageable);

    @Query(value = "SELECT u.* " +
            "FROM USUARIO u " +
            "INNER JOIN USUARIO_PROCESSO_SISTEMA ups ON ups.ID_USUARIO = u.ID_USUARIO " +
            "INNER JOIN SISTEMA s ON s.ID_SISTEMA = ups.ID_SISTEMA " +
            "WHERE ups.NUMEROPROCESSO = :numeroProcesso " +
            "AND s.SIGLA = :siglaSistema " +
            "ORDER BY NOME", nativeQuery = true)
    List<Usuario> findBySistemaAndProcesso(String siglaSistema, String numeroProcesso);

    boolean existsByLogin(String login);
}
