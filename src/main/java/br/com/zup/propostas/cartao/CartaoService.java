package br.com.zup.propostas.cartao;


import br.com.zup.propostas.cartao.CartaoResponse;
import br.com.zup.propostas.clients.ServicoCartaoClient;
import br.com.zup.propostas.compartilhado.ExecutorTransacao;
import br.com.zup.propostas.propostas.Proposta;
import br.com.zup.propostas.propostas.SolicitacaoAnaliseRequest;
import br.com.zup.propostas.propostas.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class CartaoService {
    private final ExecutorTransacao executor;
    private final ServicoCartaoClient servicoCartaoClient;
    private final String SQLQUERY="select * from proposta  where cartao is null and status= :status";

    public CartaoService(ExecutorTransacao executor, ServicoCartaoClient servicoCartaoClient) {
        this.executor = executor;
        this.servicoCartaoClient = servicoCartaoClient;
    }

    @Scheduled(fixedRate = 10000)//a cada 10 segundos submeta
    public void solicicaCriacaoDeCartao(){
        Query queryPropostaSemCartao = executor.getManager().createNativeQuery(SQLQUERY, Proposta.class);
        queryPropostaSemCartao.setParameter("status", Status.ELEGIVEL.name());
        List<Proposta> propostasSemCartao=queryPropostaSemCartao.getResultList();
        if(!propostasSemCartao.isEmpty()) {
            propostasSemCartao.forEach(proposta -> {
                servicoCartaoClient.solicitaCriacaoCartao(new SolicitacaoAnaliseRequest(proposta));
            });
        }
    }
    @Scheduled(fixedRate = 30000)
    public void solicitaCartao(){
        Query queryPropostaSemCartao = executor.getManager().createNativeQuery(SQLQUERY, Proposta.class);
        queryPropostaSemCartao.setParameter("status",Status.ELEGIVEL.name());
        List<Proposta> propostasSemCartao=queryPropostaSemCartao.getResultList();
        if(!propostasSemCartao.isEmpty()) {
            propostasSemCartao.forEach(proposta -> {
                ResponseEntity<CartaoResponse> cartaoResponseResponseEntity = servicoCartaoClient.solicitaCartao(proposta.getId().toString());
                if(cartaoResponseResponseEntity.getStatusCode().equals(HttpStatus.OK)){
                    proposta.associarCartao(cartaoResponseResponseEntity.getBody().getId());
                    executor.atualizarECommitar(proposta);
                }
            });


        }

    }
}
