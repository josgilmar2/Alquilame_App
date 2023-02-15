package com.salesianostriana.dam.alquilame.validation.annotation.dwelling;

import com.salesianostriana.dam.alquilame.validation.validator.dwelling.OnlyThreeTypesValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OnlyThreeTypesValidator.class)
@Documented
public @interface OnlyThreeTypes {

    String message() default "The type entered not match with CASA, PISO OR CHALET";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
