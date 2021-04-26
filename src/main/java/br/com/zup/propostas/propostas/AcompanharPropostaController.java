package br.com.zup.propostas.propostas;

import br.com.zup.propostas.propostas.groupsPessoa.PropostaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AcompanharPropostaController {
    private final PropostaRepository repository;

    public AcompanharPropostaController(PropostaRepository repository) {
        this.repository = repository;
    }

    @GetMapping("propostas/{id}")
    public ResponseEntity<?> acompanharProposta(@PathVariable Long id){
       Optional<Proposta> proposta=repository.findById(id);
       if (proposta.isEmpty()){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }
       return ResponseEntity.ok(new PropostaResponse(proposta.get()));

    }
}
