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


    public HealthPropostasContribuitor(HealthDataBaseIndicator dataBaseIndicator, HealthServicoFinanceiroIndicador servicoFinanceiroIndicador) {
        contributors.put("analiseFinanceira",servicoFinanceiroIndicador);
        contributors.put("mySql",dataBaseIndicator);
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
