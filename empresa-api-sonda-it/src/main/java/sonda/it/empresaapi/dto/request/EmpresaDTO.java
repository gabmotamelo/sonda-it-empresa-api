package sonda.it.empresaapi.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaDTO {

    @Setter(AccessLevel.NONE)
    @Schema(description = "ID da Empresa no banco de dados;")
    private Long id;

    @NotEmpty
    @Size(min = 2, max = 100)
    @Schema(description = "Nome da empresa;")
    private String nome;

    @Schema(description = "Email da empresa;")
    private String email;

    @NotEmpty
    @Schema(description = "Tipo da empresa;")
    private String empresa;

    @NotEmpty
    @Size(min = 14, max = 18)
    @Schema(description = "CNPJ da empresa;")
    private String cnpj;
}
