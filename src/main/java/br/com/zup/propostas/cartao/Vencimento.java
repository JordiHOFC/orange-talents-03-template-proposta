package br.com.zup.propostas.cartao;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;


public class Vencimento {
    @JsonProperty
    private String id;
    @JsonProperty
    private Integer dia;
    @JsonProperty
    private LocalDateTime dataDeCriacao;

    public Vencimento(String id, Integer dia, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }
}
