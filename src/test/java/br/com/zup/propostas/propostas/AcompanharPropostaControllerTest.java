package br.com.zup.propostas.propostas;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.net.URI;

import static br.com.zup.propostas.propostas.ResultadoSolicitacao.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.web.util.UriComponentsBuilder.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class AcompanharPropostaControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private PropostaRepository repository;

    @Test
    public void deveRetornar200eaPropostaDetalhada() throws Exception {
        Proposta proposta=new Proposta("Jordi","20.280.336/0001-62",
                "jordi@s.com","rua teclado de morais n 190, rayzer,sao gotardo-mg-38820-000",new BigDecimal("2000"));
        proposta.statusProposta(COM_RESTRICAO);
        proposta=repository.save(proposta);
        PropostaResponse propostaResponse= new PropostaResponse(proposta);
        String response= mapper.writeValueAsString(propostaResponse);
        URI uri= fromUriString("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
        mockMvc.perform(get(uri))
                .andExpect(status().isOk())
                .andExpect(content().json(response));
    }
}