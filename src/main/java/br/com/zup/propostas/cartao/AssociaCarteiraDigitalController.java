package br.com.zup.propostas.cartao;

import br.com.zup.propostas.cartao.externo.AssosicaCarteiraResponse;
import br.com.zup.propostas.cartao.externo.SolicitaCarteiraRequest;
import br.com.zup.propostas.clients.ServicoCartaoClient;
import br.com.zup.propostas.compartilhado.ExecutorTransacao;
import br.com.zup.propostas.handler.ErrorPersonalizado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
public class AssociaCarteiraDigitalController {


    private final ExecutorTransacao manager;
    private final ServicoCartaoClient client;

    public AssociaCarteiraDigitalController(ExecutorTransacao manager, ServicoCartaoClient client) {
        this.manager = manager;
        this.client = client;
    }

    @PostMapping("cartoes/{id}/carteirasdigitais")
    public ResponseEntity<?> associarCarteiraDigital(@PathVariable String id, @RequestBody SolicitaCarteiraRequest request){
        Cartao cartao = manager.getManager().find(Cartao.class, id);
        String carteira="carteira";
        if (cartao==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorPersonalizado("cartao","não cadastrado."));
        }
        ResponseEntity<AssosicaCarteiraResponse> assosicaCarteiraResponse = client.solicitaVinculoACarteira(id, request);

        if(!manager.getManager()
                .createQuery("SELECT R from " + CarteiraDigital.class.getSimpleName() + " R where R."+carteira+" =:valor")
                .setParameter("valor", request.getCarteira()).getResultList().isEmpty()){

            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ErrorPersonalizado("carteira","já associada a um cartao."));

        }
        if (!assosicaCarteiraResponse.getStatusCode().is2xxSuccessful()){
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).build();
        }

        AssosicaCarteiraResponse statusAssociacao = assosicaCarteiraResponse.getBody();
        CarteiraDigital carteiraDigital = request.paraModelo(cartao, statusAssociacao.getId());
        cartao.associarCarteira(carteiraDigital);
        manager.atualizarECommitar(cartao);
        URI uri = UriComponentsBuilder.fromUriString("cartoes/{id}/carteirasdigitais").path("/{idCarteira}").buildAndExpand(id, carteiraDigital.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
