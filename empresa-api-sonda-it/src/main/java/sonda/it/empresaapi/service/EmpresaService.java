package sonda.it.empresaapi.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sonda.it.empresaapi.dto.request.EmpresaDTO;
import sonda.it.empresaapi.dto.response.MessageResponseDTO;
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

    public EmpresaDTO findById(Long id) {
        Empresa empresa = empresaRepository.findById(id).orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
        return toDTO(empresa);
    }

    public List<EmpresaDTO> findAllByOrderByNomeAsc() {
        List<Empresa> empresas = empresaRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
        return empresas.stream().map(this::toDTO).collect(Collectors.toList());
    }

    private EmpresaDTO toDTO(Empresa empresa) {
        return new EmpresaDTO(empresa.getId(), empresa.getNome(), empresa.getEmail(), empresa.getEmpresa(), empresa.getCnpj());
    }

    private Empresa toModel(EmpresaDTO empresaDTO){
        return new Empresa(empresaDTO.getId(), empresaDTO.getNome(), empresaDTO.getEmail(), empresaDTO.getEmpresa(), empresaDTO.getCnpj());
    }
//
//    private MessageResponseDTO createMessageResponse(Long id, String message) {
//        return MessageResponseDTO
//                .builder()
//                .message(message + id)
//                .build();
//    }

}
