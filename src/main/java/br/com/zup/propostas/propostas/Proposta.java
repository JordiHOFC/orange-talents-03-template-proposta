package br.com.zup.propostas.propostas;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Proposta {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String documento;
    @Column(nullable = false)
    private String endereco;
    @Column(nullable = false)
    private BigDecimal salario;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(unique = true)
    private String cartao;

    public Proposta(String nome, String email, String documento, String endereco, BigDecimal salario) {
        this.nome = nome;
        this.email = email;
        this.documento = documento;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Proposta() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Status getStatus() {
        return status;
    }

    public String getEmail() {
        return email;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }
    public void statusProposta(ResultadoSolicitacao resultadoSolicitacao){
        if(resultadoSolicitacao.equals(ResultadoSolicitacao.SEM_RESTRICAO)){
            this.status=Status.ELEGIVEL;
        }
        else {
            this.status=Status.NAO_ELEGIVEL;
        }
    }

    public void associarCartao(String numeroCartao) {
        this.cartao=numeroCartao;
    }

}
