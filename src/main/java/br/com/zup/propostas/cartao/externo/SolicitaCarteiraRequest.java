package br.com.zup.propostas.cartao.externo;

import br.com.zup.propostas.cartao.Cartao;
import br.com.zup.propostas.cartao.CarteiraDigital;
import br.com.zup.propostas.validator.UniqueValue;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class SolicitaCarteiraRequest {
    @JsonProperty
    @Email
    @NotNull
    private String email;
    @JsonProperty
    @NotNull
    private String carteira;

    public SolicitaCarteiraRequest(String email, String carteira) {
        this.email = email;
        this.carteira = carteira;
    }
    public CarteiraDigital paraModelo(Cartao cartao, String id){
        return new CarteiraDigital(id,carteira,email,cartao);
    }

    public String getCarteira() {
        return carteira;
    }
}
