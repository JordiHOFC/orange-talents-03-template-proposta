package br.com.zup.propostas.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthContributor;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.net.Socket;
@Component
public class HealthServicoCartaoIndicator implements HealthContributor, HealthIndicator {
    private static final String URL ="http://localhost:8888/api/cartoes";

    @Override
    public Health health() {
        try (Socket socket= new Socket(new  java.net.URL(URL).getHost(),8888)){

        } catch (Exception e){
            return Health.down().withDetail("error",e.getMessage()).build();
        }
        return Health.up().build();
    }
}
