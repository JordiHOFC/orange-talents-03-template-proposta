package br.com.zup.propostas.cartao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
}
