package sonda.it.empresaapi.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import sonda.it.empresaapi.builder.EmpresaDTOBuilder;
import sonda.it.empresaapi.dto.request.EmpresaDTO;
import sonda.it.empresaapi.expection.EmpresaNotFoundException;
import sonda.it.empresaapi.service.EmpresaService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class EmpresaControllerTest {

    private static final String EMPRESA_API_URL_PATH = "/empresas";

    private static final String VALIDO_NOME_EMPRESA = "Empresa Y";

    private MockMvc mockMvc;

    @Mock
    private EmpresaService empresaService;

    @InjectMocks
    private EmpresaController empresaController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(empresaController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenGETIsCalledWithCompanyIdThenOkStatusIsReturned() throws Exception {
        // given
        EmpresaDTO empresaDTO = EmpresaDTOBuilder.builder().build().toEmpresaDTO();

        //when
        when(empresaService.findById(empresaDTO.getId())).thenReturn(empresaDTO);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(EMPRESA_API_URL_PATH + "/" + empresaDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is(empresaDTO.getNome())))
                .andExpect(jsonPath("$.email", is(empresaDTO.getEmail())))
                .andExpect(jsonPath("$.empresa", is(empresaDTO.getEmpresa())))
                .andExpect(jsonPath("$.cnpj", is(empresaDTO.getCnpj())));
    }

    @Test
    void whenGETIsCalledWithoutRegisteredIdThenNotFoundStatusIsReturned() throws Exception {
        // given
        EmpresaDTO empresaDTO = EmpresaDTOBuilder.builder().build().toEmpresaDTO();

        //when
        when(empresaService.findById(empresaDTO.getId())).thenThrow(EmpresaNotFoundException.class);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(EMPRESA_API_URL_PATH + "/" + empresaDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenGETCompaniesListIsCalledThenOkStatusIsReturned() throws Exception {
        // given
        EmpresaDTO empresaDTO = EmpresaDTOBuilder.builder().build().toEmpresaDTO();

        //when
        when(empresaService.findAll()).thenReturn(Collections.singletonList(empresaDTO));

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(EMPRESA_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome", is(empresaDTO.getNome())))
                .andExpect(jsonPath("$[0].email", is(empresaDTO.getEmail())))
                .andExpect(jsonPath("$[0].empresa", is(empresaDTO.getEmpresa())))
                .andExpect(jsonPath("$[0].cnpj", is(empresaDTO.getCnpj())));
    }

    @Test
    void whenGETListWithoutCompaniesIsCalledThenOkStatusIsReturned() throws Exception {
        // given
        EmpresaDTO empresaDTO = EmpresaDTOBuilder.builder().build().toEmpresaDTO();

        //when
        when(empresaService.findAll()).thenReturn(Collections.singletonList(empresaDTO));

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(EMPRESA_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
