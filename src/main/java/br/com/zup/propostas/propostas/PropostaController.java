package br.com.zup.propostas.propostas;

import br.com.zup.propostas.handler.ErrorPersonalizado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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
        if(existDocument(request.getDocumento())){
            ErrorPersonalizado erro=new ErrorPersonalizado("documento","já existe proposta.");
            return ResponseEntity.unprocessableEntity().body(erro);
        }
        Proposta proposta = request.paraModelo();
        manager.persist(proposta);
        URI uri=uriComponentsBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    private boolean existDocument(String documento) {
        TypedQuery<Boolean> query= manager.createQuery("select true from Proposta where documento=:doc",Boolean.class);
        query.setParameter("doc",documento);
       return !query.getResultList().isEmpty();
    }

}
