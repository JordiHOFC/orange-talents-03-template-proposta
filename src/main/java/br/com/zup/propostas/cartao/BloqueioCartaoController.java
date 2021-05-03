package br.com.zup.propostas.cartao;

import br.com.zup.propostas.handler.ErrorPersonalizado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Optional;


@RestController
public class BloqueioCartaoController {
    //1
    private final CartaoRepository cartaoRepository;

    public BloqueioCartaoController(CartaoRepository cartaoRepository) {
        this.cartaoRepository = cartaoRepository;
    }

    @PutMapping("cartoes/{id}/bloqueios")
    @Transactional
    public ResponseEntity<?> bloqueioCartao(@PathVariable String id, HttpServletRequest request){
        Optional<Cartao> cartao = cartaoRepository.findById(id);
        //1
        if (cartao.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorPersonalizado("cartao","não cadastrado."));
        }
        //1
        if (!cartao.get().getBloqueios().isEmpty()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ErrorPersonalizado("cartao","Já esta bloqueado"));
        }
        String userAgent=request.getHeader("User-Agent");
        String ip=request.getRemoteAddr();
        BloqueioCartao bloqueio=new BloqueioCartao(cartao.get(),userAgent,ip);
        cartao.get().associarBloqueio(bloqueio);
        return ResponseEntity.ok().build();

    }
}
