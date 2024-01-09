package demo.reflect.java;
 enum Gender{
     Man, Woman
 }
enum Weapons{
     ZERO,ONE
}
public class Human {
    int age;
    String name;
    private Gender gender;

    public Human(String name,int age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public void sayHello(){
        System.out.println("Hello my name is "+this.name);
    }
}
