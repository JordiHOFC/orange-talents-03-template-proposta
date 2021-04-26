package br.com.zup.propostas.propostas.groupsPessoa;

import br.com.zup.propostas.propostas.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
}
