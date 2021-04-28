package br.com.zup.propostas.cartao;

import br.com.zup.propostas.biometria.Biometria;
import br.com.zup.propostas.propostas.Proposta;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cartao {
    @Id
    private String id;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private List<Biometria> biometrias= new ArrayList<>();

    public Cartao(String id) {
        this.id = id;
    }

    public Cartao() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public List<Biometria> getBiometrias() {
        return biometrias;
    }

    public void associarBiometria(List<Biometria> biometrias){
        this.biometrias.addAll(biometrias);

    }
}
