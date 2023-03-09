package sonda.it.empresaapi.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sonda.it.empresaapi.dto.request.EmpresaDTO;
import sonda.it.empresaapi.entity.Empresa;
import sonda.it.empresaapi.expection.EmpresaNotFoundException;
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

    public EmpresaDTO findById(Long id) throws EmpresaNotFoundException {
        Empresa empresa = empresaRepository.findById(id).orElseThrow(() -> new EmpresaNotFoundException(id));
        return toDTO(empresa);
    }

    public List<EmpresaDTO> findAllByOrderByNomeAsc() {
        List<Empresa> empresas = empresaRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
        return empresas.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<EmpresaDTO> findAllByOrderByNomeDesc() {
        List<Empresa> empresas = empresaRepository.findAll(Sort.by(Sort.Direction.DESC, "nome"));
        return empresas.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public EmpresaDTO toDTO(Empresa empresa) {
        return new EmpresaDTO(empresa.getId(), empresa.getNome(), empresa.getEmail(), empresa.getEmpresa(), empresa.getCnpj());
    }

    public Empresa toModel(EmpresaDTO empresaDTO){
        return new Empresa(empresaDTO.getId(), empresaDTO.getNome(), empresaDTO.getEmail(), empresaDTO.getEmpresa(), empresaDTO.getCnpj());
    }


}
