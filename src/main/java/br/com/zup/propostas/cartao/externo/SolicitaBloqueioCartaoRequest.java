package br.com.zup.propostas.cartao.externo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonAutoDetect
public class SolicitaBloqueioCartaoRequest {
    @JsonProperty
    private String sistemaResponsavel;

    public SolicitaBloqueioCartaoRequest() {
        this.sistemaResponsavel ="SISTEMA-PROPOSTAS";
    }
}
