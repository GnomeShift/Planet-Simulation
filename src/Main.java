import java.util.*;

public class Main {
    public static void main(String[] args) {
        int size = 5;
        int days = 5;
        Random random = new Random();
        Planet earth = new Planet(size);

        Animal[] animals = {new Wolf(random.nextInt(size)), new Snake(random.nextInt(size)), new Fox(random.nextInt(size)),
                new Bear(random.nextInt(size)), new Eagle(random.nextInt(size)), new Horse(random.nextInt(size)),
                new Deer(random.nextInt(size)), new Rabbit(random.nextInt(size)), new Mouse(random.nextInt(size)),
                new Goat(random.nextInt(size)), new Sheep(random.nextInt(size)), new Boar(random.nextInt(size)),
                new Buffalo(random.nextInt(size)), new Duck(random.nextInt(size)), new Caterpillar(random.nextInt(size))};
        Plant[] plants = {new Turf(random.nextInt(size)), new Evergreen(random.nextInt(size)), new Oak(random.nextInt(size)),
                new Rice(random.nextInt(size)), new Grass(random.nextInt(size)), new Clover(random.nextInt(size))};

        earth.terraform(animals, plants, days);
    }
}
