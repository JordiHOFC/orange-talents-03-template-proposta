package br.com.zup.propostas.propostas;

import br.com.zup.propostas.propostas.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
    List<Proposta> findByCartaoIsNullAndStatusIs(Status status);
}
