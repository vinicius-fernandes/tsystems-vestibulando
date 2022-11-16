package com.vestibulando.constraints.perguntas;

import com.vestibulando.validators.perguntas.TotalRespostasValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = TotalRespostasValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface TotalRespostasConstraint {
    String message() default "A pergunta deve possuir entre 4 e 5 alternativas";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
