package br.jus.tjba.api.push.publicador.repository;

import br.jus.tjba.api.push.publicador.model.UsuarioSistema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioSistemaRepository extends JpaRepository<UsuarioSistema, Long>{
    Optional<UsuarioSistema> findByLogin(String login);
}
