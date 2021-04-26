package br.com.zup.propostas.cartao;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.JoinColumn;
import java.time.LocalDateTime;

public class Renegociacao {
    @JsonProperty
    private String id;
    @JsonProperty
    private Integer quantidade;
    @JsonProperty
    private Double valor;
    @JsonProperty
    private LocalDateTime dataDeCriacao;

    public Renegociacao(String id, Integer quantidade, Double valor, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataDeCriacao = dataDeCriacao;
    }
}
