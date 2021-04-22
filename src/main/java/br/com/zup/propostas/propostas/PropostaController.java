package br.com.zup.propostas.propostas;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class PropostaController {
    private final EntityManager manager;

    public PropostaController(EntityManager manager) {
        this.manager = manager;
    }

    @PostMapping("/propostas")
    @Transactional
    public ResponseEntity<?> cadastrarPropostas(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder uriComponentsBuilder){
        Proposta proposta = request.paraModelo();
        manager.persist(proposta);
        URI uri=uriComponentsBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
