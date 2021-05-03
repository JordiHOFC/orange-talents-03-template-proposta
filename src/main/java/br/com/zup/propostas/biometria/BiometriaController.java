package br.com.zup.propostas.biometria;

import br.com.zup.propostas.cartao.Cartao;
import br.com.zup.propostas.cartao.CartaoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Map;
import java.util.Optional;

@RestController
public class BiometriaController {
    //1
    private final CartaoRepository repository;

    public BiometriaController(CartaoRepository repository) {
        this.repository = repository;
    }


    @PostMapping("cartoes/{id}/biometrias")
    @Transactional
    public ResponseEntity<?> cadastrarBiometria(@PathVariable String id, @RequestBody @Valid BiometriaRequest request){
        Optional<Cartao> cartao = repository.findById(id);
        //1
        if (cartao.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Biometria biometria = request.paraModelo(cartao.get());
        //1
        cartao.get().associarBiometria(biometria);
        URI uri= UriComponentsBuilder.fromPath("/cartoes/{id}/biometrias/{idBiometria}").buildAndExpand(Map.of("id",id,"idBiometria",biometria.getId())).toUri();
        return ResponseEntity.created(uri).build();
    }
}
