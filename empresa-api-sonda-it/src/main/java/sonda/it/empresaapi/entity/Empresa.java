package sonda.it.empresaapi.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID da Empresa no banco de dados;")
    private Long id;

    @Column(nullable = false, unique = true)
    @Schema(description = "Nome da empresa;")
    private String nome;

    @Schema(description = "Email da empresa;")
    private String email;

    @Column(nullable = false)
    @Schema(description = "Tipo da empresa;")
    private String empresa;

    @Column(nullable = false)
    @Schema(description = "CNPJ da empresa;")
    private String cnpj;

}
