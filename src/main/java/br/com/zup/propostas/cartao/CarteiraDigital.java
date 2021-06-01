package br.com.zup.propostas.cartao;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CarteiraDigital {
    @Id
    private String id;
    private String carteira;
    private String email;
    @ManyToOne
    private Cartao cartao;

    public CarteiraDigital(String id, String carteira, String email, Cartao cartao) {
        this.id = id;
        this.carteira = carteira;
        this.email = email;
        this.cartao = cartao;
    }

    public CarteiraDigital() {
    }

    public String getId() {
        return id;
    }
}
