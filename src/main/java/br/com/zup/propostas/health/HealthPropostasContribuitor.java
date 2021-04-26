package br.com.zup.propostas.health;

import org.springframework.boot.actuate.health.CompositeHealthContributor;
import org.springframework.boot.actuate.health.HealthContributor;
import org.springframework.boot.actuate.health.NamedContributor;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
@Component
public class HealthPropostasContribuitor implements CompositeHealthContributor {

    private Map<String, HealthContributor> contributors=new LinkedHashMap<>();


    public HealthPropostasContribuitor(HealthDataBaseIndicator dataBaseIndicator, HealthServicoFinanceiroIndicador servicoFinanceiroIndicador, HealthServicoCartaoIndicator servicoCartaoIndicator) {
        contributors.put("analiseFinanceira",servicoFinanceiroIndicador);
        contributors.put("mySql",dataBaseIndicator);
        contributors.put("servicoDeCartao",servicoCartaoIndicator);
    }

    @Override
    public HealthContributor getContributor(String name) {
        return contributors.get(name);
    }

    @Override
    public Iterator<NamedContributor<HealthContributor>> iterator() {
        return contributors.entrySet().stream().map(entry->NamedContributor.of(entry.getKey(),entry.getValue())).iterator();
    }
}
