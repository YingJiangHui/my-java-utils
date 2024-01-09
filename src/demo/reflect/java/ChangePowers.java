package demo.reflect.java;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ChangePowers {
    ChangePower[] value();
}
