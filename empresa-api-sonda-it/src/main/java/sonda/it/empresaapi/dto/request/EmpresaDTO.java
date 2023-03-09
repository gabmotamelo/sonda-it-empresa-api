package sonda.it.empresaapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaDTO {

    private Long id;

    @NotEmpty
    @Size(min = 2, max = 100)
    private String name;

    private String email;

    @NotEmpty
    private String company;

    @NotEmpty
    @Size(min = 2, max = 50)
    private String cnpj;
}
