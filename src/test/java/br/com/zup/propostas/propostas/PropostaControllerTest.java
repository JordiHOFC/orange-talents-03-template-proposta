package br.com.zup.propostas.propostas;

import br.com.zup.propostas.handler.ErrorPersonalizado;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.context.event.annotation.AfterTestMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class PropostaControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private EntityManager manager;

    @Test
    public void deveRetornar201eLocation() throws Exception {

        PropostaRequest propostaRequest= new PropostaRequest("Jordi","20.280.336/0001-62",
                "jordi@s.com","rua teclado de morais n 190, rayzer,sao gotardo-mg-38820-000",new BigDecimal("2000"));
        Proposta proposta=propostaRequest.paraModelo();
        String request=mapper.writeValueAsString(propostaRequest);

        URI uri= URI.create("/propostas");
        mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isCreated())
                .andExpect(redirectedUrlPattern("**/propostas/*"));

    }
    @Test
    public void deveRetornar422eMensagemErro() throws Exception {

        PropostaRequest propostaRequest= new PropostaRequest("Jordi","20.280.336/0001-62",
                "jordi@s.com","rua teclado de morais n 190, rayzer,sao gotardo-mg-38820-000",new BigDecimal("2000"));
        Proposta proposta=propostaRequest.paraModelo();
        manager.persist(proposta);
        String request=mapper.writeValueAsString(propostaRequest);
        ErrorPersonalizado erro=new ErrorPersonalizado("documento","já existe proposta.");
        String response= mapper.writeValueAsString(erro);
        URI uri= URI.create("/propostas");
        mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().json(response));

    }


}