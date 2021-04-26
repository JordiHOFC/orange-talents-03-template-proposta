package br.com.zup.propostas.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthContributor;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.net.Socket;

@Component
public class HealthServicoFinanceiroIndicador implements HealthIndicator, HealthContributor {

    private static final String URL ="http://localhost:9999/api/solicitacao";


    @Override
    public Health health() {
        try (Socket socket= new Socket(new  java.net.URL(URL).getHost(),9999)){

        } catch (Exception e){
            return Health.down().withDetail("error",e.getMessage()).build();
        }
        return Health.up().build();
    }
}
