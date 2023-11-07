package com.dragonest.deepbackendcard.global.annotation;

import com.dragonest.deepbackendcard.global.validator.EnumValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {EnumValidator.class})
public @interface EnumValid {

    String message() default "Enum에 해당하지 않는 값입니다.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    Class<? extends Enum<?>> enumClass(); // 제약할 Enum 클래스 지정

    boolean ignoreCase() default false; // 대소문자 구분 여부
}
