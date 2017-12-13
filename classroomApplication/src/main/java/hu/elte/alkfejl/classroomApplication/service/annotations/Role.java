package hu.elte.alkfejl.classroomApplication.service.annotations;

import hu.elte.alkfejl.classroomApplication.model.User;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Role {
    User.Role[] value() default {User.Role.GUEST};
}
