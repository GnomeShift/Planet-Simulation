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

class Evergreen extends Plant {
    public Evergreen(int location) {
        super(location, "Елка", "\uD83C\uDF32");
    }
}

class Tree extends Plant {
    public Tree(int location) {
        super(location, "Дуб", "\uD83C\uDF33");
    }
}

class Rice extends Plant {
    public Rice(int location) {
        super(location, "Рис", "\uD83C\uDF3E");
    }
}

class Grass extends Plant {
    public Grass(int location) {
        super(location, "Трава", "\uD83C\uDF3F");
    }
}

class Clover extends Plant {
    public Clover(int location) {
        super(location, "Клевер", "\uD83C\uDF40");
    }
}
