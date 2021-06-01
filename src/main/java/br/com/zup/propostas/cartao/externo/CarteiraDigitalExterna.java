package br.com.zup.propostas.cartao.externo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class CarteiraDigitalExterna {
    @JsonProperty
    private String id;
    @JsonProperty
    private String email;
    @JsonProperty
    private LocalDateTime associadaEm;
    @JsonProperty
    private String emissor;

    public CarteiraDigitalExterna(String id, String email, LocalDateTime associadaEm, String emissor) {
        this.id = id;
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
    }
}
