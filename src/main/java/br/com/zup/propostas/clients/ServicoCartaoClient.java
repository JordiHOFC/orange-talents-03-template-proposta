package br.com.zup.propostas.clients;

import br.com.zup.propostas.cartao.CartaoResponse;
import br.com.zup.propostas.propostas.SolicitacaoAnaliseRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "servico-de-cartao", url = "http://localhost:8888/api/cartoes")
public interface ServicoCartaoClient {
    @PostMapping
    void solicitaCriacaoCartao(SolicitacaoAnaliseRequest request);
    @GetMapping(params = "idProposta")
    ResponseEntity<CartaoResponse> solicitaCartao(@RequestParam String idProposta);

}
