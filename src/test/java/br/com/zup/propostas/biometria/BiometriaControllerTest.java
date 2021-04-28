package br.com.zup.propostas.biometria;

import br.com.zup.propostas.cartao.Cartao;
import br.com.zup.propostas.compartilhado.ExecutorTransacao;
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
import java.net.URI;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class BiometriaControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private ExecutorTransacao manager;

    @Test
    public void deveRetornar201eURLdaBiometriaCriada() throws Exception {
        String id="6752-3358-6845-7227";
        Cartao cartao= new Cartao(id);
        manager.salvar(cartao);
        Long idBiometria=1L;
        String base64 ="é um base64";
        String baseMeiaQuatro= Base64.getEncoder().encodeToString(base64.getBytes());
        List<ImagemBase64Request> request =List.of(new ImagemBase64Request(baseMeiaQuatro));
        BiometriaRequest requests=new BiometriaRequest(request);
        URI uriRequest = UriComponentsBuilder.fromUriString("/cartoes/{id}/biometrias")
                .buildAndExpand(id).toUri();
        String location=UriComponentsBuilder.fromUriString("/cartoes/{id}/biometrias/{idBiometria}").buildAndExpand(id,idBiometria).toUriString();
        mockMvc.perform(MockMvcRequestBuilders.post(uriRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(requests)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("location", location));

    }
    @Test
    public void deveRetornar400() throws Exception {
        String id="6522-3558-6845-7227";
        Cartao cartao= new Cartao(id);
        manager.salvar(cartao);
        String base64 ="é um base64";
        List<ImagemBase64Request> request =List.of(new ImagemBase64Request(base64));
        BiometriaRequest requests=new BiometriaRequest(request);
        URI uriRequest = UriComponentsBuilder.fromUriString("/cartoes/{id}/biometrias")
                .buildAndExpand(id).toUri();
        mockMvc.perform(MockMvcRequestBuilders.post(uriRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(requests)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

}