package demo.reflect.java;

import java.lang.annotation.Annotation;
import java.lang.annotation.Target;
import java.lang.reflect.*;
import java.util.Arrays;

public class ReflectDemo {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        System.out.println("修改共属性属性");
        Man man1 = new Man(22,"JoDan",100);
        Class<?> man1Clazz = man1.getClass();
        try {
            Field fieldOfPower = man1Clazz.getField("power");
            System.out.println(man1);
            fieldOfPower.set(man1,10);

            System.out.println(man1);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("No",e);
        }
        System.out.println("修改非共有属性使用getDeclaredField");
        Man man2 = new Man(22,"JoDan",100);
        Class<?> man2Clazz = man2.getClass();
        try {
            Field fieldOfPower = man2Clazz.getDeclaredField("weapons");
            System.out.println(man2);
            fieldOfPower.setAccessible(true);
            fieldOfPower.set(man2,Weapons.ZERO);
            System.out.println(man2);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("No",e);
        }

        System.out.println("执行父类方法");
        Man man3 = new Man(22,"JoDan3",100);
        Class<?> man3Clazz = man3.getClass().getSuperclass();
        try {

            Method fieldOfPower = man3Clazz.getDeclaredMethod("sayHello");
            fieldOfPower.invoke(man3);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        System.out.println("执行方法带参数");
        Man man4 = new Man(22,"JoDan3",100);
        Class<?> man4Clazz = man3.getClass();
        try {
            Method fieldOfPower = man4Clazz.getDeclaredMethod("getMyBrithYear",int.class);
            System.out.println("My both in " + fieldOfPower.invoke(man4,2024));
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        System.out.println("执行构造函数");
        Class<?> man5Clazz = Man.class;
        try {
            Constructor<?> fieldConstructor = man5Clazz.getConstructor(int.class, String.class,int.class);
            Man man = (Man) fieldConstructor.newInstance(100,"Dan",100);
            System.out.println(man);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
        System.out.println("获取 Repeatable 注解");
        Class<?> man6Clazz = Man.class;
        Annotation[] annotations = man6Clazz.getAnnotations();
        for (Annotation annotation: annotations){
            if(annotation instanceof ChangePowers t){
                for(ChangePower c : t.value()){
                    System.out.println(c.value());
                }
            }
        }
        System.out.println("使用getAnnotationsByType获取注解");
        ChangePower[] annotations2 = Man.class.getAnnotationsByType(ChangePower.class);
        for(ChangePower annotation2: annotations2){
            System.out.println(annotation2.value());
        }
    }
}
