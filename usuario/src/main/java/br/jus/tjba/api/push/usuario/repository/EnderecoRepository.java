package br.jus.tjba.api.push.usuario.repository;

import br.jus.tjba.api.push.usuario.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
