import java.util.*;

public abstract class Animal {
    private int location;
    private boolean isAlive;
    private String name;
    private String icon;
    private int energy;

    public Animal(int location, String name, String icon, int energy) {
        this.location = location;
        this.isAlive = true;
        this.name = name;
        this.icon = icon;
        this.energy = energy;
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
        if (isAlive && energy > 0) {
            Random random = new Random();
            int newLocation = location + random.nextInt(3) - 1;
            newLocation = Math.max(0, Math.min(newLocation, cell.planet.cells.size() - 1));
            location = newLocation;
            energy--;
        }
        return location;
    }

    public abstract Animal multiply();

    public abstract boolean canMultiply();


    public List<String> interact(Animal other) {
        List<String> events = new ArrayList<>();
        if (isAlive && other.isAlive()) {
            if (canHunt(other)) {
                int energyPlus = hunt(other);
                events.add(getName() + " съел(-а) " + other.getName() + " (+" + energyPlus + "⚡️)");
                energy += energyPlus;
                other.die();
            }
        }
        return events;
    }

    public abstract int hunt(Animal other);

    public abstract boolean canHunt(Animal other);

    public List<String> interact(Plant plant) {
        List<String> events = new ArrayList<>();
        if (isAlive && plant.isAlive()) {
            if (canEat(plant)) {
                int energyPlus = eat(plant);
                events.add(getName() + " съел(-а) " + plant.getName() + " (+" + energyPlus + "⚡️)");
                energy += energyPlus;
                plant.die();
            }
        }
        return events;
    }

    public abstract int eat(Plant plant);

    public abstract boolean canEat(Plant plant);

    public void die() {
        isAlive = false;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }
}
