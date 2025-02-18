package com.example.tutorialv2.controller;


import com.example.tutorialv2.data.vo.v1.PerfilVO;
import com.example.tutorialv2.data.vo.v1.security.TokenVO;
import com.example.tutorialv2.repository.PerfilRepository;
import com.example.tutorialv2.security.jwt.JwtTokenProvider;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PerfilTest {

    private static final long ID_UM = 1L;

    private static final String ENDPOINT_ROLE = "ADMIN";
    @MockBean
    private PerfilRepository perfilRepository;
    @Autowired
    private MockMvc mvc;

    @MockBean
    private JwtTokenProvider tokenProvider;

    @DisplayName("Token Não informado")
    @Test
    void tokenInvalido() throws Exception {
        this.mvc.perform(this.getRequestBuilder())
                .andExpect(status().isForbidden());
    }

    @DisplayName("Criar Perfil")
     @Test
    void criarPerfil() throws Exception {
         PerfilVO perfil = PerfilVO.builder().idPerfil(ID_UM).nome("Perfil Teste").build();

         given(perfilRepository.findById(ID_UM)).willReturn(Optional.empty());

         var newToken = tokenProvider.getAuthentication("");


         this.mvc.perform(this.getRequestBuilder()
                 .with(jwt())
                 .content(this.asJsonString(perfil)))
                 .andExpect(status().isOk());


     }



     private MockHttpServletRequestBuilder getRequestBuilder(){
         return MockMvcRequestBuilders
                 .post("/api/v1/perfil")
                 .contentType(MediaType.APPLICATION_JSON)
                 .accept(MediaType.APPLICATION_JSON);
     }

    private String asJsonString(final Object obj) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, Boolean.FALSE);
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


   


}
