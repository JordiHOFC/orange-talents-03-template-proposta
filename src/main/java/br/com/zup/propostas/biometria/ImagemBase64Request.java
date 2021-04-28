package br.com.zup.propostas.biometria;

import br.com.zup.propostas.validator.IsBase64;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class ImagemBase64Request {

    @JsonProperty
    @NotBlank
    @IsBase64
    private String image;

    @JsonCreator
    public ImagemBase64Request(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }
}
