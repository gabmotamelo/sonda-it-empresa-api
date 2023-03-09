package sonda.it.empresaapi.builder;

import lombok.Builder;
import sonda.it.empresaapi.dto.request.EmpresaDTO;

@Builder
public class EmpresaDTOBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String nome = "Empresa X";

    @Builder.Default
    private String email = "xxxxx@xxxxxx.com";

    private String empresa = "Empresa";

    private String cnpj = "XX.XXX.XXX/YYYY-ZZ";

    public EmpresaDTO toEmpresaDTO(){
        return new EmpresaDTO(
                id,
                nome,
                email,
                empresa,
                cnpj
        );
    }

}
