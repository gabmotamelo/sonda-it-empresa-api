package sonda.it.empresaapi.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sonda.it.empresaapi.dto.request.EmpresaDTO;
import sonda.it.empresaapi.entity.Empresa;
import sonda.it.empresaapi.repository.EmpresaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmpresaService {

    private EmpresaRepository empresaRepository;

    public List<EmpresaDTO> findAll() {
        List<Empresa> empresas = empresaRepository.findAll();
        return empresas.stream().map(this::toDTO).collect(Collectors.toList());
    }

    private EmpresaDTO toDTO(Empresa empresa) {
        EmpresaDTO empresaDTO = new EmpresaDTO();
        empresaDTO.setId(empresa.getId());
        empresaDTO.setNome(empresa.getNome());
        empresaDTO.setEmail(empresa.getEmail());
        empresaDTO.setEmpresa(empresa.getEmpresa());
        empresa.setCnpj(empresa.getCnpj());
        return empresaDTO;
    }

    private Empresa toModel(EmpresaDTO empresaDTO){
        Empresa empresa = new Empresa();
        empresa.setId(empresaDTO.getId());
        empresa.setNome(empresa.getNome());
        empresa.setEmail(empresa.getEmail());
        empresa.setEmpresa(empresa.getEmpresa());
        empresa.setCnpj(empresa.getCnpj());
        return empresa;
    }

}
