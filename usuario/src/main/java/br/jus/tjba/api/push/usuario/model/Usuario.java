package br.jus.tjba.api.push.usuario.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "USUARIO")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO", nullable = false)
    private Long idUsuario;

    @Column(name = "LOGIN", nullable = false)
    private String login;

    @Column(name = "SENHA", nullable = false)
    private String senha;

    @Column(name = "NOME", length = 250)
    private String nome;

    @Column(name = "CPF", nullable = false, length = 14)
    private String cpf;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ENDERECO_ID", referencedColumnName = "ID_ENDERECO")
    private Endereco endereco;

    @OneToMany(mappedBy = "usuario")
    private List<UsuarioProcessoSistema> sistemas;
}