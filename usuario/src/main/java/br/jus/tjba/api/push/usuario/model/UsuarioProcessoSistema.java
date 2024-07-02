package br.jus.tjba.api.push.usuario.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "USUARIO_PROCESSO_SISTEMA")
@IdClass(UsuarioProcessoSistemaId.class) // Indica que esta entidade possui uma chave composta
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioProcessoSistema {

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", nullable = false)
    @Id // Parte da chave primária composta
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "ID_SISTEMA", referencedColumnName = "ID_SISTEMA", nullable = false)
    @Id // Parte da chave primária composta
    private Sistema sistema;

    @Column(name = "NUMEROPROCESSO", nullable = false, length = 25)
    private String numeroProcesso;
}
