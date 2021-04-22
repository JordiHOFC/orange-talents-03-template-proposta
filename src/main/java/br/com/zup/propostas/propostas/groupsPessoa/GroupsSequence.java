package br.com.zup.propostas.propostas.groupsPessoa;

import br.com.zup.propostas.propostas.PropostaRequest;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class GroupsSequence implements DefaultGroupSequenceProvider<PropostaRequest> {
    @Override
    public List<Class<?>> getValidationGroups(PropostaRequest request) {
        List<Class<?>>groups=new ArrayList<>();
        groups.add(PropostaRequest.class);
        if (request!=null){
            if (request.getDocumento().length()==18){
                groups.add(PessoaJuridica.class);
            }
            else if(request.getDocumento().length()==14){
                groups.add(PessoaFisica.class);
            }
        }
        return groups;
    }
}
