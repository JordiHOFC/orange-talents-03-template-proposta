package br.com.zup.propostas.cartao;

import br.com.zup.propostas.cartao.externo.AvisoViagem;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AvisoDeViagemRequest {
    @JsonProperty @NotNull
    private String destino;

    @JsonProperty
    @Future
    @NotNull
    private LocalDateTime validoAte;

    public  AvisoDeViagemRequest(String destino, LocalDateTime validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public AvisoDeViagem paraModelo(Cartao cartao,String userAgent, String ip){
        return new AvisoDeViagem(destino, validoAte,cartao,userAgent,ip);
    }
    public AvisoViagem paraAvisoViagem(){
        return new AvisoViagem(destino, LocalDate.from(validoAte));
    }
}
