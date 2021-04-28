package br.com.zup.propostas.biometria;

import br.com.zup.propostas.cartao.Cartao;
import br.com.zup.propostas.compartilhado.ExecutorTransacao;
import br.com.zup.propostas.propostas.Proposta;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.internal.matchers.EndsWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.web.util.UriComponentsBuilder.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class BiometriaControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private ExecutorTransacao manager;

    @Test
    public void deveRetornar201eURLdaBiometriaCriada() throws Exception {
        Proposta proposta=new Proposta("Jordi","20.280.336/0001-62",
                "jordi@s.com","rua teclado de morais n 190, rayzer,sao gotardo-mg-38820-000",new BigDecimal("2000"));
        String id="6752-3358-6845-7227";
         manager.salvar(proposta);
        Cartao cartao= new Cartao(id,proposta);
        manager.atualizarECommitar(cartao);
        String baseMeiaQuatro= Base64.getEncoder().encodeToString("base64".getBytes());
        BiometriaRequest requests=new BiometriaRequest(baseMeiaQuatro);
        URI uriRequest = fromUriString("/cartoes/{id}/biometrias")
                .buildAndExpand(id).toUri();
        mockMvc.perform(post(uriRequest)
                .contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsString(requests)))
                .andExpect(status().isCreated())
                .andExpect(redirectedUrlPattern("/cartoes/*/biometrias/*"));


    }
    @Test
    public void deveRetornar400() throws Exception {
        Proposta proposta=new Proposta("Jordi","20.280.336/0001-62",
                "jordi@s.com","rua teclado de morais n 190, rayzer,sao gotardo-mg-38820-000",new BigDecimal("2000"));
        String id="6752-3358-6845-7227";
        manager.salvar(proposta);
        Cartao cartao= new Cartao(id,proposta);
        manager.atualizarECommitar(cartao);
        String base64 ="é um base64";
        BiometriaRequest requests=new BiometriaRequest(base64);
        URI uriRequest = fromUriString("/cartoes/{id}/biometrias")
                .buildAndExpand(id).toUri();
        mockMvc.perform(post(uriRequest)
                .contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsString(requests)))
                .andExpect(status().isBadRequest());


    }

}