public class Predators extends Animal {

    public Predators(int location, String name, String icon) {
        super(location, name, icon);
    }

    public boolean canHunt(Animal other) {
        return other instanceof Herbivores;
    }

    public boolean canEat(Plant plant) {
        return false;
    }

    public boolean canMultiply() {
        return true;
    }

    public String toString() {
        return "Клетка: " + getLocation() + ", " + getName() + getIcon();
    }
}

class Wolf extends Predators {
    public Wolf(int location) {
        super(location, "Волк", "\uD83D\uDC3A");
    }
}

class Snake extends Predators {
    public Snake(int location) {
        super(location, "Удав", "\uD83D\uDC0D");
    }
}

class Fox extends Predators {
    public Fox(int location) {
        super(location, "Лиса", "\uD83E\uDD8A");
    }
}

class Bear extends Predators {
    public Bear(int location) {
        super(location, "Медведь", "\uD83D\uDC3B");
    }
}

class Eagle extends Predators {
    public Eagle(int location) {
        super(location, "Орел", "\uD83E\uDD85");
    }
}
