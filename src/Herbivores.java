class Herbivores extends Animal {

    public Herbivores(int location, String name, String icon) {
        super(location, name, icon);
    }

    public boolean canHunt(Animal other) {
        return false;
    }

    public boolean canEat(Plant plant) {
        return true;
    }

    public String toString() {
        return "Клетка: " + getLocation() + ", " + getName() + getIcon();
    }
}

class Horse extends Herbivores {
    public Horse(int location) {
        super(location, "Лошадь", "\uD83D\uDC0E");
    }
}

class Deer extends Herbivores {
    public Deer(int location) {
        super(location, "Олень", "\uD83E\uDD8C");
    }
}

class Rabbit extends Herbivores {
    public Rabbit(int location) {
        super(location, "Кролик", "\uD83D\uDC07");
    }
}

class Mouse extends Herbivores {
    public Mouse(int location) {
        super(location, "Мышь", "\uD83D\uDC01");
    }
}

class Goat extends Herbivores {
    public Goat(int location) {
        super(location, "Коза", "\uD83D\uDC10");
    }
}

class Sheep extends Herbivores {
    public Sheep(int location) {
        super(location, "Овца", "\uD83D\uDC11");
    }
}

class Boar extends Herbivores {
    public Boar(int location) {
        super(location, "Кабан", "\uD83D\uDC17");
    }
}

class Buffalo extends Herbivores {
    public Buffalo(int location) {
        super(location, "Буйвол", "\uD83D\uDC03");
    }
}

class Duck extends Herbivores {
    public Duck(int location) {
        super(location, "Утка", "\uD83E\uDD86");
    }

    @Override
    public boolean canHunt(Animal other) {
        return other instanceof Caterpillar;
    }
}

class Caterpillar extends Herbivores {
    public Caterpillar(int location) {
        super(location, "Гусеница", "\uD83D\uDC1B");
    }
}
