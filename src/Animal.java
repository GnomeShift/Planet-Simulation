import java.util.*;

public abstract class Animal {
    private int location;
    private boolean isAlive;
    private String name;
    private String icon;

    public Animal(int location, String name, String icon) {
        this.location = location;
        this.isAlive = true;
        this.name = name;
        this.icon = icon;
    }

    public int getLocation() {
        return location;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public int move(Cell cell) {
        if (isAlive) {
            Random random = new Random();
            int newLocation = location + random.nextInt(3) - 1;
            if (newLocation >= 0 && newLocation < cell.planet.cells.size()) {
                location = newLocation;
            }
        }
        return location;
    }

    public Animal multiply(Cell cell) {
        if (cell.getAnimals(this).isAlive()) {
            for (Animal other : cell.animals) {
                if (other != this && other.isAlive() && other.getClass().equals(this.getClass())) {
                    Animal newAnimal = null;
                    try {
                        newAnimal = this.getClass().getConstructor(int.class).newInstance(cell.location);
                    }
                    catch (Exception e) {
                        System.out.println("Ошибочка! " + e.getMessage());
                    }
                    return newAnimal;
                }
            }
        }
        return null;
    }

    public abstract boolean canMultiply();

    public List<String> interact(Animal other) {
        List<String> events = new ArrayList<>();
        if (isAlive && other.isAlive()) {
            if (canHunt(other)) {
                events.add(getName() + " съел(-а) " + other.getName());
                other.die();
            }
        }
        return events;
    }

    public abstract boolean canHunt(Animal other);

    public List<String> interact(Plant plant) {
        List<String> events = new ArrayList<>();
        if (isAlive) {
            if (canEat(plant)) {
                events.add(getName() + " съел(-а) " + plant.getName());
                plant.die();
            }
        }
        return events;
    }

    public abstract boolean canEat(Plant plant);

    public void die() {
        isAlive = false;
    }
}
