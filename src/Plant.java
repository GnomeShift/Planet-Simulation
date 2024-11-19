public abstract class Plant {
    private int location;
    private boolean isAlive = true;
    private String name;
    private String icon;
    private int energy;

    public Plant(int location, String name, String icon, int energy) {
        this.location = location;
        this.name = name;
        this.icon = icon;
        this.energy = energy;
    }

    public int getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void die() {
        isAlive = false;
    }

    public int getEnergy() {
        return energy;
    }
}

class Turf extends Plant {
    public Turf(int location) {
        super(location, "Земля", "\uD83C\uDF31", 0);
    }
}
