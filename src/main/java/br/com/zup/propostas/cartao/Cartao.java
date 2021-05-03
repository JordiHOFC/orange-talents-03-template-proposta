package br.com.zup.propostas.cartao;

import br.com.zup.propostas.biometria.Biometria;
import br.com.zup.propostas.propostas.Proposta;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Cartao {
    @Id
    private String id;

    @OneToOne
    private Proposta proposta;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)
    private List<Biometria> biometrias= new ArrayList<>();

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)
    private List<BloqueioCartao> bloqueios=new ArrayList<>();

    public Cartao(String id, Proposta proposta) {
        this.id = id;
        this.proposta = proposta;
    }

    public Cartao() {
    }

    public String getId() {
        return id;
    }

    public List<Biometria> getBiometrias() {
        return biometrias;
    }

    public void associarBiometria(Biometria biometria){
        this.biometrias.add(biometria);

    }
    public void associarBloqueio(BloqueioCartao bloqueio){
        this.bloqueios.add(bloqueio);
    }

    public List<BloqueioCartao> getBloqueios() {
        return bloqueios;
    }
}
