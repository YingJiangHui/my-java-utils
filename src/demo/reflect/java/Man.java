package demo.reflect.java;

import java.lang.annotation.Repeatable;

@ChangePower(value = 10)
@ChangePower(value = 20)
@ChangePower(value = 10)
public class Man extends Human{
    static Gender gender = Gender.Man;
    private Weapons weapons;
    public int power;
    public Man(int age, String name,int power) {
        super(name, age , gender);
        this.power = power;
        this.weapons = Weapons.ONE;
    }

    public int getMyBrithYear(int thisYear){
        return thisYear - this.age;
    }

    @Override
    public String toString() {
        return "Man{" +
                "weapons=" + weapons +
                ", power=" + power +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
