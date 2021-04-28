package br.com.zup.propostas.biometria;

import br.com.zup.propostas.cartao.Cartao;
import br.com.zup.propostas.compartilhado.ExecutorTransacao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
public class BiometriaController {

    private final ExecutorTransacao executorTransacao;

    public BiometriaController(ExecutorTransacao executorTransacao) {
        this.executorTransacao = executorTransacao;
    }


    @PostMapping("cartoes/{id}/biometrias")
    public ResponseEntity<?> cadastrarBiometria(@PathVariable String id, @RequestBody @Valid BiometriaRequest request){
        Cartao cartao = executorTransacao.getManager().find(Cartao.class, id);
        if (cartao==null){
            return ResponseEntity.notFound().build();
        }
        Biometria biometria = request.paraModelo(cartao);
        executorTransacao.salvar(biometria);
        cartao.associarBiometria(biometria);
        executorTransacao.atualizarECommitar(cartao);
        URI uri= UriComponentsBuilder.fromPath("/cartoes/{id}/biometrias/{idBiometria}").buildAndExpand(Map.of("id",id,"idBiometria",biometria.getId())).toUri();
        return ResponseEntity.created(uri).build();
    }
}
