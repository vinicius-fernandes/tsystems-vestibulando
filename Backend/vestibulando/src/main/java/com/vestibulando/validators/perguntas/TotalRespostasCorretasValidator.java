package com.vestibulando.validators.perguntas;

import com.vestibulando.constraints.perguntas.TotalRespostasCorretasConstraint;
import com.vestibulando.entities.Resposta;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

public class TotalRespostasCorretasValidator implements ConstraintValidator<TotalRespostasCorretasConstraint, Set<Resposta>> {
    @Override
    public boolean isValid(Set<Resposta> respostas, ConstraintValidatorContext constraintValidatorContext) {
        return respostas.stream().filter(Resposta::getCorreta).count() == 1;
    }
}
