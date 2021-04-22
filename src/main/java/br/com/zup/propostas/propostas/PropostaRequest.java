package br.com.zup.propostas.propostas;

import br.com.zup.propostas.propostas.groupsPessoa.GroupsSequence;
import br.com.zup.propostas.propostas.groupsPessoa.PessoaFisica;
import br.com.zup.propostas.propostas.groupsPessoa.PessoaJuridica;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
@GroupSequenceProvider(value = GroupsSequence.class)
public class PropostaRequest {
    @JsonProperty
    @NotBlank
    private String nome;
    @JsonProperty
    @NotBlank
    @CPF(groups = PessoaFisica.class)
    @CNPJ(groups = PessoaJuridica.class)
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
