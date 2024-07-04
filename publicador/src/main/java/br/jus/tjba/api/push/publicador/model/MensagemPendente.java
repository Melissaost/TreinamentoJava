package br.jus.tjba.api.push.publicador.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MENSAGEM_PENDENTE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MensagemPendente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MENSAGEM_PENDENTE", nullable = false)
    private Long id;

    @Column(name = "NUMERO_PROCESSO", nullable = false)
    private String numeroProcesso;

    @Column(name = "MENSAGEM", nullable = false)
    private String mensagem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO_SISTEMA")
    private UsuarioSistema usuarioSistema;
}
