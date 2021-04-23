package br.com.zup.propostas.propostas;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SolicitacaoAnaliseRequest {
    @JsonProperty
    private String documento;
    @JsonProperty
    private String idProposta;
    @JsonProperty
    private String nome;

    public SolicitacaoAnaliseRequest(Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.idProposta = proposta.getId().toString();
        this.nome = proposta.getNome();
    }
}
