package br.com.zup.propostas.cartao;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class Bloqueio {
    @JsonProperty
    private String id;
    @JsonProperty
    private LocalDateTime bloqueadoEm;
    @JsonProperty
    private String sistemaResponsavel;
    @JsonProperty
    private boolean ativo;

    public Bloqueio(String id, LocalDateTime bloqueadoEm, String sistemaResponsavel, boolean ativo) {
        this.id = id;
        this.bloqueadoEm = bloqueadoEm;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
    }
}
