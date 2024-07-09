package br.jus.tjba.api.push.publicador.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "USUARIO_SISTEMA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioSistema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO_SISTEMA", nullable = false)
    private Long id;

    @Column(name = "LOGIN", nullable = false, unique = true)
    private String login;

    @Column(name = "CHAVE_ACESSO", nullable = false)
    private String chaveAcesso;

    @OneToMany(mappedBy = "usuarioSistema", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MensagemPendente> mensagensPendentes;
}
