package br.com.zup.propostas.cartao.externo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class ResultadoBloqueioCartaoResponse {
    @JsonProperty
    private StatusBloqueioResponse resultado;
    @JsonCreator
    public ResultadoBloqueioCartaoResponse(StatusBloqueioResponse resultado) {
        this.resultado = resultado;
    }

    public StatusBloqueioResponse getResultado() {
        return resultado;
    }
}
