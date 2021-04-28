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

    @JsonProperty
    @NotNull
    @NotBlank
    @IsBase64
    private String image;

    @JsonCreator
    public BiometriaRequest(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public Biometria paraModelo(Cartao cartao){
        return new Biometria(image,cartao);
    }


}
