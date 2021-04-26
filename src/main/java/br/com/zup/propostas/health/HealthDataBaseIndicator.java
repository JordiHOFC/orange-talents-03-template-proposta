package br.com.zup.propostas.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthContributor;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Component("database")
public class HealthDataBaseIndicator implements HealthIndicator, HealthContributor {

    @Autowired
    private DataSource source;

    @Override
    public Health health() {
        try(Connection conn=source.getConnection()){
            Statement stmt=conn.createStatement();
            stmt.execute("select * from proposta");
        }
        catch (Exception e){
            return Health.outOfService().withException(e).build();
        }
        return Health.up().build();
    }
}
