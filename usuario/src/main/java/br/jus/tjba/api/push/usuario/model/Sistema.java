package br.jus.tjba.api.push.usuario.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "SISTEMA")
@Builder
public class Sistema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SISTEMA", nullable = false)
    private Long id;

    @Column(name = "SIGLA", nullable = false, unique = true)
    private String sigla;

    @OneToMany(mappedBy = "sistema")
    private List<UsuarioProcessoSistema> usuarios;
}
