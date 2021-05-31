package br.com.zup.propostas.cartao.externo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class AvisoViagem {
    @JsonProperty
    private String destino;
    @JsonProperty
    private LocalDate validoAte;

    public AvisoViagem(String destino, LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }
}
