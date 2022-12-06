package com.vestibulando.constraints.perguntas;

import com.vestibulando.validators.perguntas.TotalRespostasCorretasValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = TotalRespostasCorretasValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface TotalRespostasCorretasConstraint {
    String message() default "A pergunta deve possuir uma única alternativa correta";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
