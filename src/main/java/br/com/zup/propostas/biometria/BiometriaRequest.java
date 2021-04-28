package br.com.zup.propostas.biometria;

import br.com.zup.propostas.cartao.Cartao;
import br.com.zup.propostas.validator.IsBase64;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@JsonAutoDetect
public class BiometriaRequest {

    @Size(min = 1)
    @JsonProperty
    @NotNull
    @Valid
    private List<ImagemBase64Request> image;

    @JsonCreator
    public BiometriaRequest(List<ImagemBase64Request>  image) {
        this.image = image;
    }

    public List<ImagemBase64Request> getImage() {
        return image;
    }

    public List<Biometria> paraModelo(Cartao cartao){
        return image.stream().map(i->new Biometria(i.getImage(),cartao)).collect(Collectors.toList());
    }


}
