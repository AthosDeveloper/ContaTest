package com.factory.contabancaria.controller;
import com.factory.contabancaria.DTOs.CadastrarContaDTO;
import com.factory.contabancaria.DTOs.ExibirContaPeloIdDTO;
import com.factory.contabancaria.model.ContasModel;

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
import java.math.BigDecimal;
import java.util.Arrays;
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
    @BeforeEach
    public void setup(){
        c1 = new ContasModel(1L, "2426", "614", "fernando", new BigDecimal("640.52"), new BigDecimal(714), "cartãoDeCredito", new BigDecimal("914"));
        c2 = new ContasModel(2L, "4152", "1461", "fernando", new BigDecimal("4141.12"), new BigDecimal("614652"), "cartaoCredito", new BigDecimal("842"));
cadastrarContaDTO1 = new CadastrarContaDTO(c1);
cadastrarContaDTO2 = new CadastrarContaDTO(c2);
    }
    @Test
    public void findAllAccounts() throws Exception{
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
   public void findById(){
        when(contasService.exibeContaPorId(c1.getId()))
                .thenReturn(c1);
        mockMvc.perform(get("/api/contas{id}", c1.getId()).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(cadastrarContaDTO1)))
                .andExpect(status()
                        .isOk())
                .andDo(print());


    }
}

