package sonda.it.empresaapi.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sonda.it.empresaapi.builder.EmpresaDTOBuilder;
import sonda.it.empresaapi.dto.request.EmpresaDTO;
import sonda.it.empresaapi.entity.Empresa;
import sonda.it.empresaapi.expection.EmpresaNotFoundException;
import sonda.it.empresaapi.repository.EmpresaRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmpresaServiceTest {

    @Mock
    private EmpresaRepository empresaRepository;

    @InjectMocks
    private EmpresaService empresaService;

    @Test
    void whenGETCalledWithValidCompanyIdThenReturnACompany() throws EmpresaNotFoundException {
        // given
        EmpresaDTO expectedFoundEmpresaDTO = EmpresaDTOBuilder.builder().build().toEmpresaDTO();
        Empresa expectedFoundEmpresa = empresaService.toModel(expectedFoundEmpresaDTO);

        // when
        when(empresaRepository.findById(expectedFoundEmpresa.getId())).thenReturn(Optional.of(expectedFoundEmpresa));

        // then
        EmpresaDTO foundEmpresaDTO = empresaService.findById(expectedFoundEmpresa.getId());

        assertThat(foundEmpresaDTO, is(equalTo(expectedFoundEmpresaDTO)));
    }

    @Test
    void whenGETCalledWithNotRegisteredCompanyIdThenThrowAnException() {
        // given
        EmpresaDTO expectedFoundEmpresaDTO = EmpresaDTOBuilder.builder().build().toEmpresaDTO();

        // when
        when(empresaRepository.findById(expectedFoundEmpresaDTO.getId())).thenReturn(Optional.empty());

        // then
        assertThrows(EmpresaNotFoundException.class, () -> empresaService.findById(expectedFoundEmpresaDTO.getId()));
    }

    @Test
    void whenGETListCompanyCalledThenReturnAListOfCompanies() {
        // given
        EmpresaDTO expectedFoundEmpresaDTO = EmpresaDTOBuilder.builder().build().toEmpresaDTO();
        Empresa expectedFoundEmpresa = empresaService.toModel(expectedFoundEmpresaDTO);

        //when
        when(empresaRepository.findAll()).thenReturn(Collections.singletonList(expectedFoundEmpresa));

        //then
        List<EmpresaDTO> foundListCompaniesDTO = empresaService.findAll();

        assertThat(foundListCompaniesDTO, is(not(empty())));
        assertThat(foundListCompaniesDTO.get(0), is(equalTo(expectedFoundEmpresaDTO)));
    }

    @Test
    void whenGETCalledWithListCompaniesThenReturnAnEmptyListOfCompanies() {
        //when
        when(empresaRepository.findAll()).thenReturn(Collections.EMPTY_LIST);

        //then
        List<EmpresaDTO> foundListCompaniesDTO = empresaService.findAll();

        assertThat(foundListCompaniesDTO, is(empty()));
    }

}
