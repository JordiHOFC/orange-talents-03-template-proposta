package br.com.zup.propostas.biometria;

import br.com.zup.propostas.cartao.Cartao;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Entity
public class Biometria {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @PastOrPresent
    private LocalDateTime criadoEm=LocalDateTime.now();
    @Column
    @Type(type = "text")
    private String image;

    @ManyToOne
    private Cartao cartao;

    public Biometria(String image, Cartao cartao) {
        this.image = image;
        this.cartao=cartao;
    }

    public Biometria() {
    }

}
