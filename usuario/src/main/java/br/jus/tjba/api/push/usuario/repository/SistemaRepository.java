package br.jus.tjba.api.push.usuario.repository;

import br.jus.tjba.api.push.usuario.model.Sistema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SistemaRepository extends JpaRepository<Sistema, Long> {
    Optional<Sistema> findBySigla(String sigla);
}
