package br.com.zup.propostas.validator;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue,String> {
    private final EntityManager manager;
    private String campo;
    private Class<?> classeDominio;

    public UniqueValueValidator(EntityManager manager) {

        this.manager = manager;
    }

    @Override
    public void initialize(UniqueValue constraintAnnotation) {
       this.campo=constraintAnnotation.domainField();
       this.classeDominio=constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(String uniqueValue, ConstraintValidatorContext constraintValidatorContext) {
        Query valorUnico = manager
                .createQuery("SELECT R from " + classeDominio.getSimpleName() + " R where " + campo + " =:valor")
                .setParameter("valor", uniqueValue);

        return valorUnico.getResultList().isEmpty();
    }
}
