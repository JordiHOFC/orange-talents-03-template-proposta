package br.com.zup.propostas.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsBase64Validator.class)
public @interface IsBase64 {
    String message() default "String nao pertece ao encoder base64";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
