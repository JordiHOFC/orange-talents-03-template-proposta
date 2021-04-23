package br.com.zup.propostas.handler;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ErrorPersonalizado {
    @JsonProperty
    private List<String> mensagens= new ArrayList<>();

    public ErrorPersonalizado(String campo,String mensagem) {
        mensagens.add(String.format("campo %s %s ", campo, mensagem));
    }
}
