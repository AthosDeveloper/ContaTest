package com.factory.contabancaria.controller;
import com.factory.contabancaria.DTOs.CadastrarContaDTO;
import com.factory.contabancaria.DTOs.ExibirContaPeloIdDTO;
import com.factory.contabancaria.model.ContasModel;

import com.factory.contabancaria.model.factory.ContaFactory;
import com.factory.contabancaria.service.ContasService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;


import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(MockitoExtension.class)
@WebMvcTest(ContasController.class)
public class ContasControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContasService contasService;
@Autowired
    private ObjectMapper mapper;
    private static ContasModel c1, c2;
    private  static CadastrarContaDTO cadastrarContaDTO1, cadastrarContaDTO2;
    private static ExibirContaPeloIdDTO exibirContaPeloIdDTO1, exibirContaPeloIdDTO2;

    private static ContaFactory contaFactory;
    @BeforeEach
    public void setup(){
        c1 = new ContasModel(1L, "2426", "614", "fernando", new BigDecimal("640.52"), new BigDecimal(714), "cartãoDeCredito", new BigDecimal("914"));
        c2 = new ContasModel(2L, "4152", "1461", "fernando", new BigDecimal("4141.12"), new BigDecimal("614652"), "cartaoCredito", new BigDecimal("842"));
cadastrarContaDTO1 = new CadastrarContaDTO(c1);
cadastrarContaDTO2 = new CadastrarContaDTO(c2);
   exibirContaPeloIdDTO1 = new ExibirContaPeloIdDTO(c1);
    }
    @Test
    public void testarSeEstaListandoAsContasCorretamenteESeOMetodoChamouUmaVez() throws Exception{
        when(contasService.listarContas())
                .thenReturn(Arrays.asList(c1, c2));
        mockMvc.perform(get("/api/contas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(Arrays.asList(cadastrarContaDTO1, cadastrarContaDTO2))))
                .andDo(print());
   verify(contasService).listarContas();
   verify(contasService, times(1)).listarContas();

    }
    @Test
   public void MeRetorneUmStatusOk_quandoEuBuscarUmaContaPorId() throws Exception{



            when(contasService.exibeContaPorId(c1.getId()))
                    .thenReturn(Optional.of(c1));
            mockMvc.perform(get("/api/contas/getId/{id}", c1.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(exibirContaPeloIdDTO1)))
                    .andExpect(status()
                            .isOk())
                    .andDo(print());


    }
    @Test
public void meRetorneStatusIsCreated_quandoCadasTrarUmaNovaConta() throws Exception{



            when(contasService.cadastrar(any(), eq(contaFactory)))
                    .thenReturn(c1);
            mockMvc.perform(post("/api/contas")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(cadastrarContaDTO1)))
                    .andExpect(status().isCreated())
                    .andDo(print());
        verify(contasService, times(1)).cadastrar(any(), eq(contaFactory))  ;

    }
@Test
    public void MeRetorneStatusNoContent_quandoDeletarUmaContaPorId() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/contas/{id}", c1.getId()))
                .andExpect(status()
                        .isNoContent());
}
}

