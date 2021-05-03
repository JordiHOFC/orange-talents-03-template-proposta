package br.com.zup.propostas.cartao.externo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Parcela {
    @JsonProperty
    private String id;
    @JsonProperty
    private Integer quantidade;
    @JsonProperty
    private Double valor;

    public Parcela(String id, Integer quantidade, Double valor) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
    }
}
