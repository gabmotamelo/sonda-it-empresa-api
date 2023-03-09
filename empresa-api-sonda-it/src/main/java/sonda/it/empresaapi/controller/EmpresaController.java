package sonda.it.empresaapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sonda.it.empresaapi.dto.request.EmpresaDTO;
import sonda.it.empresaapi.expection.EmpresaNotFoundException;
import sonda.it.empresaapi.service.EmpresaService;

import java.util.List;

@RestController
@RequestMapping("/empresas")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmpresaController {
    // Swagger UI - http://localhost:8080/swagger-ui/index.html

    private EmpresaService empresaService;

    @Operation(summary = "Listar todas as empresas.")
    @GetMapping
    public List<EmpresaDTO> findAll() {
        return empresaService.findAll();
    }

    @Operation(summary = "Retorna empresa pelo id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Empresa não encontrada."),
            @ApiResponse(responseCode = "200", description = "Empresa encontrada.")
    })
    @GetMapping("/{id}")
    public EmpresaDTO findById(@PathVariable Long id) throws EmpresaNotFoundException {
        return empresaService.findById(id);
    }

    @Operation(summary = "Retorna empresas pelo nome em ordem crescente.")
    @GetMapping("/order-by-name-asc")
    public List<EmpresaDTO> findAllByOrderByNomeAsc() {
        return empresaService.findAllByOrderByNomeAsc();
    }

    @Operation(summary = "Retorna empresas pelo nome em ordem decrescente.")
    @GetMapping("/order-by-name-desc")
    public List<EmpresaDTO> findAllByOrderByNomeDesc() {
        return empresaService.findAllByOrderByNomeDesc();
    }

}
