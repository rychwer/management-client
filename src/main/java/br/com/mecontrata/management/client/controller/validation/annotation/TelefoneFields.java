package br.com.mecontrata.management.client.controller.validation.annotation;

import br.com.mecontrata.management.client.controller.validation.TelefoneFieldsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TelefoneFieldsValidator.class)
public @interface TelefoneFields {

    String message() default "A lista de telefônes é inválida";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
