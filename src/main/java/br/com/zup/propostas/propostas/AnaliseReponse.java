package br.com.zup.propostas.propostas;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AnaliseReponse {
    @JsonProperty
    private String documento;
    @JsonProperty
    private String idProposta;
    @JsonProperty
    private String nome;
    @JsonProperty
    private ResultadoSolicitacao resultadoSolicitacao;

    public String getDocumento() {
        return documento;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public String getNome() {
        return nome;
    }

    public ResultadoSolicitacao getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }
}
