public class Main {
    public static void main(String[] args) {
        int size = 20;
        Planet earth = new Planet(size);

        Animal wolf = new Wolf(0);
        Animal snake = new Snake(1);
        Animal fox = new Fox(2);
        Animal bear = new Bear(3);
        Animal eagle = new Eagle(4);

        Animal horse = new Horse(5);
        Animal deer = new Deer(6);
        Animal rabbit = new Rabbit(7);
        Animal mouse = new Mouse(8);
        Animal goat = new Goat(9);
        Animal sheep = new Sheep(10);
        Animal boar = new Boar(11);
        Animal buffalo = new Buffalo(12);
        Animal caterpillar = new Caterpillar(13);

        Plant turf = new Turf(14);
        Plant evergreen = new Evergreen(15);
        Plant tree = new Tree(16);
        Plant rice = new Rice(17);
        Plant grass = new Grass(18);
        Plant clover = new Clover(19);

        Animal[] animals = {wolf, snake, fox, bear, eagle, horse, deer, rabbit, mouse, goat, sheep, boar, buffalo, caterpillar};
        Plant[] plants = {turf, evergreen, tree, rice, grass, clover};

        for (int i = 0; i < animals.length; i++) {
            earth.addAnimal(animals[i]);
        }
        for (int i = 0; i < plants.length; i++) {
            earth.addPlant(plants[i]);
        }

        for (int i = 0; i < 10; i++) {
            System.out.println("День " + (i + 1) + ":");
            earth.simulateDay();
            System.out.println();
        }
    }
}
