package br.com.zup.propostas.cartao;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.time.LocalDateTime;
@Entity
public class AvisoDeViagem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String destino;
    @Future
    private LocalDateTime validoAte;

    @CreationTimestamp
    private LocalDateTime criadoEm=LocalDateTime.now();

    @ManyToOne
    private Cartao cartao;

    private String userAgent;

    private String ip;

    public AvisoDeViagem(String destino, LocalDateTime validoAte, Cartao cartao, String userAgent, String ip) {
        this.destino = destino;
        this.validoAte = validoAte;
        this.cartao = cartao;
        this.userAgent = userAgent;
        this.ip = ip;
    }

    public AvisoDeViagem() {
    }
}
