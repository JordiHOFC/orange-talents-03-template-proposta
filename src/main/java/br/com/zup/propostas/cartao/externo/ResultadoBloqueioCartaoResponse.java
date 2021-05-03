package br.com.zup.propostas.cartao.externo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class ResultadoBloqueioCartaoResponse {
    @JsonProperty
    private StatusBloqueio resultado;
    @JsonCreator
    public ResultadoBloqueioCartaoResponse(StatusBloqueio resultado) {
        this.resultado = resultado;
    }

    public StatusBloqueio getResultado() {
        return resultado;
    }
}
