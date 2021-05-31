package br.com.zup.propostas.propostas;

import br.com.zup.propostas.validator.DocumentoValido;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class PropostaRequest {
    @JsonProperty
    @NotBlank
    private String nome;
    @JsonProperty
    @NotBlank
    @DocumentoValido
    private String documento;
    @JsonProperty
    @NotBlank
    @Email
    private String email;
    @JsonProperty
    @NotBlank
    private String endereco;
    @JsonProperty
    @NotNull
    @Positive
    private BigDecimal salarioBruto;

    public PropostaRequest(String nome, String documento, String email, String endereco, BigDecimal salarioBruto) {
        this.nome = nome;
        this.documento = documento;
        this.email = email;
        this.endereco = endereco;
        this.salarioBruto = salarioBruto;
    }

    public String getDocumento() {
        return documento;
    }
    public Proposta paraModelo(){
        return new Proposta(nome,email,documento,endereco,salarioBruto);
    }
}
