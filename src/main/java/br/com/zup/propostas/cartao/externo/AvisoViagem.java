package br.com.zup.propostas.cartao.externo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class AvisoViagem {
    @JsonProperty
    private String destino;
    @JsonProperty
    private LocalDateTime validoAte;

    public AvisoViagem(String destino, LocalDateTime validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }
}
