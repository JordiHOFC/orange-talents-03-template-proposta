package br.com.zup.propostas.cartao;

import br.com.zup.propostas.cartao.externo.ResultadoBloqueioCartaoResponse;
import br.com.zup.propostas.cartao.externo.SolicitaBloqueioCartaoRequest;
import br.com.zup.propostas.cartao.externo.StatusBloqueioResponse;
import br.com.zup.propostas.clients.ServicoCartaoClient;
import br.com.zup.propostas.handler.ErrorPersonalizado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


@RestController
public class BloqueioCartaoController {
    //1
    private final CartaoRepository cartaoRepository;
    //1
    private final ServicoCartaoClient  cartaoClient;

    public BloqueioCartaoController(CartaoRepository cartaoRepository, ServicoCartaoClient cartaoClient) {
        this.cartaoRepository = cartaoRepository;
        this.cartaoClient = cartaoClient;
    }

    @PutMapping("cartoes/{id}/bloqueios")
    public ResponseEntity<?> bloqueioCartao(@PathVariable String id, HttpServletRequest request){
        Optional<Cartao> cartao = cartaoRepository.findById(id);
        //1
        if (cartao.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorPersonalizado("cartao","não cadastrado."));
        }
        //1
        if (!cartao.get().getBloqueios().isEmpty()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ErrorPersonalizado("cartao","Já esta bloqueado"));
        }
        String userAgent=request.getHeader("User-Agent");
        String ip=request.getRemoteAddr();
        BloqueioCartao bloqueio=new BloqueioCartao(cartao.get(),userAgent,ip);
        ResponseEntity<ResultadoBloqueioCartaoResponse> bloqueioCartaoResponse = cartaoClient.solicitaBloqueio(id, new SolicitaBloqueioCartaoRequest());
        //1
        if(bloqueioCartaoResponse.getBody().getResultado().equals(StatusBloqueioResponse.BLOQUEADO)){
            cartao.get().associarBloqueio(bloqueio);
        }
        cartaoRepository.save(cartao.get());
        return ResponseEntity.ok().build();
    }
}
