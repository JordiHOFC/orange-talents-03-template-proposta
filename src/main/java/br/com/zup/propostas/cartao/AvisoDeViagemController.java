package br.com.zup.propostas.cartao;

import br.com.zup.propostas.clients.ServicoCartaoClient;
import br.com.zup.propostas.handler.ErrorPersonalizado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class AvisoDeViagemController {
    private final CartaoRepository repository;
    private final ServicoCartaoClient cartaoClient;

    public AvisoDeViagemController(CartaoRepository repository, ServicoCartaoClient cartaoClient) {
        this.repository = repository;
        this.cartaoClient = cartaoClient;
    }

    @PostMapping( "cartoes/{id}/avisoviagens")
    @Transactional
    public ResponseEntity<?> cadastrarAvisoDeViagem(@Valid @RequestBody AvisoDeViagemRequest avisoDeViagemRequest, @PathVariable String id, HttpServletRequest request){
        String userAgent=request.getHeader("User-Agent");
        String ip=request.getRemoteAddr();
        final Optional<Cartao> possivelCartao = repository.findById(id);
        if (possivelCartao.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorPersonalizado("cartao","não cadastrado."));
        }
        ResponseEntity<?> reponse = cartaoClient.solicitaCriarAvisoParaViagem(id, avisoDeViagemRequest.paraAvisoViagem());
        if(!reponse.getStatusCode().equals(HttpStatus.OK)){
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new ErrorPersonalizado("conexao","indisponivel"));
        }
        AvisoDeViagem avisoDeViagem = avisoDeViagemRequest.paraModelo(possivelCartao.get(), userAgent, ip);
        possivelCartao.get().associarAvisoImagem(avisoDeViagem);

        return ResponseEntity.ok().build();
    }
}