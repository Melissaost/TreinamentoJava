package br.jus.tjba.api.push.usuario.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "ENDERECO")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ENDERECO", nullable = false)
    private Long idEndereco;

    @Column(name = "RUA", nullable = false)
    private String rua;

    @Column(name = "BAIRRO", nullable = false)
    private String bairro;

    @Column(name = "NUMERO")
    private String numero;

    @Column(name = "CIDADE", nullable = false)
    private String cidade;

    @Column(name = "UF", length = 2, nullable = false)
    private String uf;

    @Column(name = "CEP", length = 10, nullable = false)
    private String cep;
}