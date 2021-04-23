package br.com.zup.propostas.clients;

import br.com.zup.propostas.propostas.AnaliseReponse;
import br.com.zup.propostas.propostas.SolicitacaoAnaliseRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@FeignClient(value = "analise-financeira", url =  "http://localhost:9999/api/solicitacao")
public interface AnaliseFinanceiraClient {
    @RequestMapping(method = RequestMethod.POST)
    AnaliseReponse solicitarAnalise(SolicitacaoAnaliseRequest request);
}
