package demo.reflect.java;

public class Woman extends Human{
    static Gender gender = Gender.Woman;
    public Woman(int age, String name) {
        super(name, age , gender);
    }
}
