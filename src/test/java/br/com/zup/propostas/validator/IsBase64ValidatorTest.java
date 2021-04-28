package br.com.zup.propostas.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.validation.ConstraintValidatorContext;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

class IsBase64ValidatorTest {
    ConstraintValidatorContext ctx= Mockito.mock(ConstraintValidatorContext.class);
    IsBase64Validator validator= new IsBase64Validator();

    @Test
    public void deveSerValido() {
        String texto = "E um base64";
        boolean valid = validator.isValid(Base64.getEncoder().encodeToString(texto.getBytes()), ctx);
        Assertions.assertTrue(valid);
    }
    @Test
    public void naoDeveSerValido() {
        String texto = "Nao e um base64";
        boolean notValid = validator.isValid(texto, ctx);
        Assertions.assertFalse(notValid);
    }

}