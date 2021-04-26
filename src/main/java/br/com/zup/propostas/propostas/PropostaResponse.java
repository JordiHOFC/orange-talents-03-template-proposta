package br.com.zup.propostas.propostas;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PropostaResponse {
    @JsonProperty
    private Long id;
    @JsonProperty
    private Status status;
    @JsonProperty
    private String nome;
    @JsonProperty
    private boolean possuicartao;

    public PropostaResponse(Proposta proposta) {
        this.id= proposta.getId();
        this.nome= proposta.getNome();
        this.status=proposta.getStatus();
        if (proposta.getStatus().equals(Status.ELEGIVEL)){
            this.possuicartao=true;
        }
    }
}
