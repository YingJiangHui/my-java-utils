package demo.reflect.java;

import java.lang.annotation.*;

/**
 * 修改类的 power
 */
@Target(ElementType.TYPE) // 作用在什么类上例如类、属性、方法等
@Retention(RetentionPolicy.RUNTIME) // 作用在运行时的哪个阶段
@Repeatable(ChangePowers.class)
public @interface ChangePower {
    int value() default 100; // 加上默认值之后使用时可以不用设置
}
