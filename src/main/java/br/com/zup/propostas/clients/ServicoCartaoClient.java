package br.com.zup.propostas.clients;

import br.com.zup.propostas.cartao.AvisoDeViagemRequest;
import br.com.zup.propostas.cartao.externo.*;
import br.com.zup.propostas.propostas.SolicitacaoAnaliseRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "servico-de-cartao", url = "${propostas.env.systemaccounts}")
public interface ServicoCartaoClient {
    @PostMapping
    ResponseEntity<CartaoResponse> solicitaCartao(SolicitacaoAnaliseRequest request);
    @RequestMapping(method = RequestMethod.POST, path = "/{id}/bloqueios")
    ResponseEntity<ResultadoBloqueioCartaoResponse> solicitaBloqueio(@PathVariable String id, @RequestBody SolicitaBloqueioCartaoRequest request);
    @RequestMapping(method = RequestMethod.POST, path = "/{id}/avisos")
    ResponseEntity<?> solicitaCriarAvisoParaViagem(@PathVariable String id, @RequestBody AvisoViagem avisoViagem);
    @RequestMapping(method = RequestMethod.POST, path = "/{id}/carteiras")
    ResponseEntity<AssosicaCarteiraResponse> solicitaVinculoACarteira(@PathVariable String id, @RequestBody SolicitaCarteiraRequest avisoViagem);

}

