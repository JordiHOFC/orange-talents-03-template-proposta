package br.com.zup.propostas.cartao;


import br.com.zup.propostas.clients.ServicoCartaoClient;
import br.com.zup.propostas.compartilhado.ExecutorTransacao;
import br.com.zup.propostas.propostas.Proposta;
import br.com.zup.propostas.propostas.PropostaRepository;
import br.com.zup.propostas.propostas.SolicitacaoAnaliseRequest;
import br.com.zup.propostas.propostas.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.List;

@Component
public class CartaoService {
    private final ExecutorTransacao executor;
    private final ServicoCartaoClient servicoCartaoClient;
    private final PropostaRepository propostaRepository;

    public CartaoService(ExecutorTransacao executor, ServicoCartaoClient servicoCartaoClient, PropostaRepository propostaRepository) {
        this.executor = executor;
        this.servicoCartaoClient = servicoCartaoClient;
        this.propostaRepository = propostaRepository;
    }

    @Scheduled(fixedRate = 30000)//a cada 10 segundos submeta
    public void solicicaCriacaoDeCartao(){
        List<Proposta> propostasSemCartao=propostaRepository.findByCartaoIsNullAndStatusIs(Status.ELEGIVEL);
        if(!propostasSemCartao.isEmpty()) {
            propostasSemCartao.forEach(proposta -> {
                ResponseEntity<CartaoResponse> cartaoResponseResponseEntity =  servicoCartaoClient.solicitaCartao(new SolicitacaoAnaliseRequest(proposta));
                if(cartaoResponseResponseEntity.getStatusCode().equals(HttpStatus.CREATED)){
                    proposta.associarCartao(cartaoResponseResponseEntity.getBody().getId());
                    executor.atualizarECommitar(proposta);
                }
            });
        }
    }

}
