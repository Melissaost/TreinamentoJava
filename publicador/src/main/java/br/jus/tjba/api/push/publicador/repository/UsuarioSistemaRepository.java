package br.jus.tjba.api.push.publicador.repository;

import br.jus.tjba.api.push.publicador.model.UsuarioSistema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioSistemaRepository extends JpaRepository<UsuarioSistema, Long>{

    @Query(value = "SELECT us.* " +
            "FROM USUARIO_SISTEMA us " +
            "WHERE us.LOGIN = :login ", nativeQuery = true)
    Optional<UsuarioSistema> findByLoginUsuarioSistema(String login);

    boolean existsByLogin(String login);

    UserDetails findByLogin(String login);
}
