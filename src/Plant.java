public abstract class Plant {
    private int location;
    private boolean isAlive;
    private String name;
    private String icon;

    public Plant(int location, String name, String icon) {
        this.location = location;
        this.isAlive = true;
        this.name = name;
        this.icon = icon;
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
}

class Turf extends Plant {
    public Turf(int location) {
        super(location, "Земля", "\uD83C\uDF31");
    }
}
