import java.util.*;

public class Main {
    public static void main(String[] args) {
        int size = 5;
        int days = 5;
        Random random = new Random();
        Planet earth = new Planet(size);

        Animal[] animals = {new Wolf(random.nextInt(size), 10), new Snake(random.nextInt(size), 10), new Fox(random.nextInt(size), 10),
                new Bear(random.nextInt(size), 10), new Eagle(random.nextInt(size), 10), new Horse(random.nextInt(size), 10),
                new Deer(random.nextInt(size), 10), new Rabbit(random.nextInt(size), 10), new Mouse(random.nextInt(size), 10),
                new Goat(random.nextInt(size), 10), new Sheep(random.nextInt(size), 10), new Boar(random.nextInt(size), 10),
                new Buffalo(random.nextInt(size), 10), new Duck(random.nextInt(size), 10), new Caterpillar(random.nextInt(size), 10)};
        Plant[] plants = {new Turf(random.nextInt(size)), new Evergreen(random.nextInt(size)), new Oak(random.nextInt(size)),
                new Rice(random.nextInt(size)), new Grass(random.nextInt(size)), new Clover(random.nextInt(size))};

        earth.terraform(animals, plants, days);
    }
}
