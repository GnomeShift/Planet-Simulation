import java.util.*;

public abstract class Animal {
    private int location;
    private boolean isAlive;
    private String name;
    private String icon;
    private int energy;
    private static final Map<String, Map<String, Double>> huntChances = new HashMap<>();
    static {
        huntChances.put("Волк", Map.of("Лошадь", 0.10, "Олень", 0.15, "Кролик", 0.60, "Мышь", 0.80, "Коза", 0.60, "Овца", 0.70, "Кабан", 0.15, "Буйвол", 0.10, "Утка", 0.40));
        huntChances.put("Удав", Map.of("Кролик", 0.20, "Мышь", 0.40, "Утка", 0.10));
        huntChances.put("Лиса", Map.of("Кролик", 0.70, "Мышь", 0.90, "Утка", 0.60));
        huntChances.put("Медведь", Map.of("Лошадь", 0.40, "Олень", 0.80, "Кролик", 0.80, "Мышь", 0.90, "Коза", 0.70, "Овца", 0.70, "Кабан", 0.50, "Буйвол", 0.20, "Утка", 0.10));
        huntChances.put("Орел", Map.of("Кролик", 0.90, "Мышь", 0.90, "Утка", 0.80));
        huntChances.put("Утка", Map.of("Гусеница", 0.90));
    }

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
        if (isAlive && other.isAlive() && this != other) {
            String hunterName = this.getName();
            String victimName = other.getName();
            if (huntChances.containsKey(hunterName) && huntChances.get(hunterName).containsKey(victimName) && canHunt(other)) {
                double chance = huntChances.get(hunterName).get(victimName);
                if (new Random().nextDouble() < chance) {
                    int energyPlus = hunt(other);
                    events.add(getName() + " съел(-а) " + other.getName() + " (+" + energyPlus + "⚡️)");
                    energy += energyPlus;
                    other.die();
                }
                else {
                    events.add(getName() + " не смог съесть " + other.getName() + " (-3⚡️)");
                    energy -= 3;
                }
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
