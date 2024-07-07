package br.jus.tjba.api.push.publicador.repository;

import br.jus.tjba.api.push.publicador.model.MensagemPendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensagemPendenteRepository extends JpaRepository<MensagemPendente, Long>{

    @Query("SELECT m FROM MensagemPendente m")
    List<MensagemPendente> findUsuariosPendentes();
}
