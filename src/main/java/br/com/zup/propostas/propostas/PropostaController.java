package br.com.zup.propostas.propostas;

import br.com.zup.propostas.clients.AnaliseFinanceiraClient;
import br.com.zup.propostas.compartilhado.ExecutorTransacao;
import br.com.zup.propostas.handler.ErrorPersonalizado;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class PropostaController {
    private final ExecutorTransacao executor;
    private final AnaliseFinanceiraClient client;

    public PropostaController(ExecutorTransacao executor, AnaliseFinanceiraClient client) {
        this.executor = executor;
        this.client = client;
    }

    @PostMapping("/propostas")
    public ResponseEntity<?> cadastrarPropostas(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder uriComponentsBuilder){
        if(existDocument(request.getDocumento())){
            ErrorPersonalizado erro=new ErrorPersonalizado("documento","já existe proposta.");
            return ResponseEntity.unprocessableEntity().body(erro);
        }
        Proposta proposta = request.paraModelo();
        executor.salvar(proposta);
        ResponseEntity<AnaliseReponse> reponse = client.solicitarAnalise(new SolicitacaoAnaliseRequest(proposta));
        proposta.statusProposta(reponse.getBody().getResultadoSolicitacao());
        executor.atualizarECommitar(proposta);

        URI uri=uriComponentsBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    private boolean existDocument(String documento) {
        List<?> list = executor.executeQueryList("select true from Proposta where documento=:doc", documento, "doc");
        return !list.isEmpty();
    }

}
