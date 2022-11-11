package com.vestibulando.validators.perguntas;

import com.vestibulando.constraints.perguntas.TotalRespostasConstraint;
import com.vestibulando.entities.Resposta;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class TotalRespostasValidator implements ConstraintValidator<TotalRespostasConstraint, List<Resposta>> {
    @Override
    public boolean isValid(List<Resposta> respostas, ConstraintValidatorContext constraintValidatorContext) {
        return respostas.size() >= 4 && respostas.size() <=6;
    }
}
