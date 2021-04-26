package br.com.zup.propostas.cartao;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@JsonAutoDetect
public class CartaoResponse {
    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    private List<Bloqueio> bloqueios=new ArrayList<>();
    private List<AvisoViagem> avisos= new ArrayList<>();
    private List<CarteiraDigital> carteiras= new ArrayList<>();
    private List<Parcela> parcelas= new ArrayList<>();
    private Integer limite;
    private Renegociacao renegociacao;
    private Vencimento vencimento;
    private String idProposta;

    @JsonCreator
    public CartaoResponse(String id, LocalDateTime emitidoEm, String titular, List<Bloqueio> bloqueios, List<AvisoViagem> avisos, List<CarteiraDigital> carteiras, List<Parcela> parcelas, Integer limite, Renegociacao renegociacao, Vencimento vencimento, String idProposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.bloqueios = bloqueios;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.parcelas = parcelas;
        this.limite = limite;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
        this.idProposta = idProposta;
    }

    public String getId() {
        return id;
    }
}
