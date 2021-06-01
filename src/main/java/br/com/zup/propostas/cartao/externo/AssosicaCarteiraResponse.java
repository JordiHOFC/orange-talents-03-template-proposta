package br.com.zup.propostas.cartao.externo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AssosicaCarteiraResponse {

    private StatusAssociacao resultado;

    private String id;

    public AssosicaCarteiraResponse(StatusAssociacao resultado, String id) {
        this.resultado = resultado;
        this.id = id;
    }

    public StatusAssociacao getResultado() {
        return resultado;
    }

    public String getId() {
        return id;
    }
}
