import java.util.Map;

public class Main {
    public static void main(String[] args) {
    Map  <String, Integer > map = Map.of("Серый", 3, "Белый", 5, "Рыжий", 2);
    map.forEach((name, age)->{
        System.out.println(name + " -> " + age);
    });
        map.forEach((name, age)->{
            if (age>3){
                System.out.println("На волю " + name);
            }
        });

    }

}
