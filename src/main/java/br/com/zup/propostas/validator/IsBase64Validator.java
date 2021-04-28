package br.com.zup.propostas.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Base64;

public class IsBase64Validator implements ConstraintValidator<IsBase64,String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try{
            Base64.getDecoder().decode(s);
        }catch (Exception e) {
            return false;
        }
        return true;
    }
}
