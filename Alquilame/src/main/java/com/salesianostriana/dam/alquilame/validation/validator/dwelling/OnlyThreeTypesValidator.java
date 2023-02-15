package com.salesianostriana.dam.alquilame.validation.validator.dwelling;

import com.salesianostriana.dam.alquilame.validation.annotation.dwelling.OnlyThreeTypes;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OnlyThreeTypesValidator implements ConstraintValidator<OnlyThreeTypes, String> {

    private final String casa = "CASA";
    private final String piso = "PISO";
    private final String chalet = "CHALET";
    
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !s.equalsIgnoreCase(casa) || !s.equalsIgnoreCase(piso) || s.equalsIgnoreCase(chalet);
    }
}
