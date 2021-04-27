package br.com.zup.propostas.cartao;

import br.com.zup.propostas.compartilhado.ExecutorTransacao;
import br.com.zup.propostas.propostas.Proposta;
import br.com.zup.propostas.propostas.ResultadoSolicitacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import javax.persistence.OneToOne;
import javax.persistence.TypedQuery;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
class CartaoServiceTest {
   @Autowired
   ExecutorTransacao executorTransacao;
   Proposta proposta,proposta2;
   @Autowired
   CartaoService service;

    @BeforeEach
    public void Before() {
       proposta=new Proposta("Jordi","20.280.336/0001-62",
                "jordi@s.com","rua teclado de morais n 190, rayzer,sao gotardo-mg-38820-000",new BigDecimal("2000"));
        proposta.statusProposta(ResultadoSolicitacao.SEM_RESTRICAO);
        proposta2=new Proposta("Jordi","20.250.336/0001-62",
                "jordi@s.com","rua teclado de morais n 190, rayzer,sao gotardo-mg-38820-000",new BigDecimal("2000"));
        proposta2.statusProposta(ResultadoSolicitacao.SEM_RESTRICAO);


    }



   @Test
   @DisplayName("As propostas sem cartao da lista devem receber  cartao")
   public void teste1(){
       executorTransacao.salvar(proposta);
       executorTransacao.salvar(proposta2);
       service.solicicaCriacaoDeCartao();
       TypedQuery<Proposta> queryTodasPropostas = executorTransacao.getManager().createQuery("select r from Proposta r ", Proposta.class);
       List<Proposta> todasPropostas= queryTodasPropostas.getResultList();
       Assertions.assertEquals(todasPropostas.size(),2);
       Assertions.assertNotNull(todasPropostas.get(0).getCartao());
       Assertions.assertNotNull(todasPropostas.get(1).getCartao());
   }

}