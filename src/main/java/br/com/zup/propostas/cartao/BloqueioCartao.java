package br.com.zup.propostas.cartao;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.rmi.server.UID;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class BloqueioCartao {
    @Id
    private String id;

    @ManyToOne  @JoinColumn(nullable = false)
    private Cartao cartao;

    @PastOrPresent  @CreationTimestamp
    private LocalDateTime criadoEm=LocalDateTime.now();
    @Column(nullable = false)
    private String userAgent;
    @Column(nullable = false)
    private String ip;

    public BloqueioCartao(Cartao cartao, String userAgent, String ip) {
        this.id= UUID.randomUUID().toString();
        this.cartao = cartao;
        this.userAgent = userAgent;
        this.ip = ip;
    }

}
