import java.util.*;

public class Planet {
    public List<Cell> cells;
    private Random random = new Random();

    public Planet(int size) {
        cells = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            cells.add(new Cell(i, this));
        }
    }

    public void terraform(Animal[] animals, Plant[] plants, int days) {
        for (Cell cell : cells) {
            cell.addAnimal(animals[random.nextInt(animals.length)]);
            cell.addPlant(plants[random.nextInt(plants.length)]);
        }

        for (int i = 0; i < days; i++) {
            System.out.println("День " + (i + 1) + ":");
            simulateDay();
            System.out.println();
        }
    }

    public void simulateDay() {
        List<String> dayEvents = new ArrayList<>();

        for (Cell cell : cells) {
            cell.moveAnimals();
        }

        for (Cell cell : cells) {
            cell.multiplyAnimals();
        }

        for (Cell cell : cells) {
            dayEvents.addAll(cell.interact());
        }

        System.out.println("События дня:");
        if (dayEvents.isEmpty()) {
            System.out.println("Ничего интересного...");
        }
        else {
            for (String event : dayEvents) {
                System.out.println(event);
            }
        }

        for (Cell cell : cells) {
            System.out.println(cell);
        }
    }
}

class Cell {
    public int location;
    public List<Animal> animals = new ArrayList<>();
    private List<Plant> plants = new ArrayList<>();
    public Planet planet;

    public Cell(int location, Planet planet) {
        this.location = location;
        this.planet = planet;
    }

    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public Animal getAnimals(Animal animal) {
        for (Animal finder : animals) {
            if (animal.equals(finder)) {
                return animal;
            }
        }
        return null;
    }

    public void moveAnimals() {
        List<Animal> movedAnimals = new ArrayList<>();
        for (Animal animal : animals) {
            if (animal.isAlive()) {
                int newLocation = animal.move(this);
                if (newLocation == animal.getLocation()) {
                    movedAnimals.add(animal);
                }
            }
        }

        for (Animal animal : movedAnimals) {
            animals.remove(animal);
            planet.cells.get(animal.getLocation()).addAnimal(animal);
        }
    }

    public List<Animal> multiplyAnimals() {
    Iterator<Animal> it = animals.iterator();
    boolean hasMultiplied = false;
    List<Animal> multipliedAnimals = new ArrayList<>();
        while (it.hasNext()) {
            Animal animal = it.next();
            if (animal.canMultiply() && animal.isAlive() && !hasMultiplied) {
                Animal newAnimal = animal.multiply(this);
                if (newAnimal != null) {
                    planet.cells.get(newAnimal.getLocation()).addAnimal(newAnimal);
                    hasMultiplied = true;
                    multipliedAnimals.add(newAnimal);
                    return multipliedAnimals;
                }
            }
        }
        return multipliedAnimals;
    }

    public List<String> interact() {
        List<String> events = new ArrayList<>();

        for (Animal animal1 : animals) {
            if (animal1.isAlive()) {
                for (Animal animal2 : animals) {
                    if (animal1 != animal2 && animal2.isAlive() && Math.abs(animal1.getLocation() - animal2.getLocation()) <= 1) {
                        events.addAll(animal1.interact(animal2));
                    }
                }
            }
        }

        for (Animal animal : animals) {
            if (animal.isAlive()) {
                for (Plant plant : plants) {
                    events.addAll(animal.interact(plant));
                }
            }
        }

        return events;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Клетка " + location + ": ");
        if (!plants.isEmpty()) {
            for (Plant plant : plants) {
                sb.append(plant.getName() + plant.getIcon() + (plant.isAlive() ? "" : "(\uD83D\uDC80)") + " ");
            }
        }
        if (!animals.isEmpty()) {
            for (Animal animal : animals) {
                sb.append(animal.getName() + animal.getIcon() + (animal.isAlive() ? "" : "(\uD83D\uDC80)") + " ");
            }
        }
        return sb.toString();
    }
}
