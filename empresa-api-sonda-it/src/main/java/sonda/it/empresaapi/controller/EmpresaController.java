package sonda.it.empresaapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sonda.it.empresaapi.dto.request.EmpresaDTO;
import sonda.it.empresaapi.service.EmpresaService;

import java.util.List;

@RestController
@RequestMapping("/empresas")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmpresaController {
    private EmpresaService empresaService;

    @GetMapping
    public List<EmpresaDTO> findAll() {
        return empresaService.findAll();
    }

    @GetMapping("/{id}")
    public EmpresaDTO findById(@PathVariable Long id) {
        return empresaService.findById(id);
    }

//    @GetMapping("/order-by-name-asc")
//    public List<EmpresaDTO> findAllByOrderByNameAsc() {
//        return empresaService.findAllByOrderByNameAsc();
//    }
}
