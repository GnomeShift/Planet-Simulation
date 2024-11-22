class Predators extends Animal {
    public Predators(int location, String name, String icon, int energy) {
        super(location, name, icon, energy);
    }

    @Override
    public boolean canHunt(Animal other) {
        return other instanceof Herbivores;
    }

    @Override
    public int hunt(Animal other) {
        return 10;
    }

    @Override
    public boolean canEat(Plant plant) {
        return false;
    }

    @Override
    public int eat(Plant plant) {
        return 0;
    }

    @Override
    public Animal multiply() {
        if (canMultiply()) {
            try {
                return this.getClass().getConstructor(int.class, int.class).newInstance(this.getLocation(), 5);
            } catch (Exception e) {
                System.out.println("Ошибка при размножении: " + e.getMessage());
                return null;
            }
        }
        return null;
    }

    @Override
    public boolean canMultiply() {
        return getEnergy() >= 10;
    }


    public String toString() {
        return "Клетка: " + getLocation() + ", " + getName() + getIcon() + ", (" + getEnergy() + "⚡️)";
    }
}

class Wolf extends Predators {
    public Wolf(int location, int energy) {
        super(location, "Волк", "\uD83D\uDC3A", energy);
    }
}

class Snake extends Predators {
    public Snake(int location, int energy) {
        super(location, "Удав", "\uD83D\uDC0D", energy);
    }
}

class Fox extends Predators {
    public Fox(int location, int energy) {
        super(location, "Лиса", "\uD83E\uDD8A", energy);
    }
}

class Bear extends Predators {
    public Bear(int location, int energy) {
        super(location, "Медведь", "\uD83D\uDC3B", energy);
    }
}

class Eagle extends Predators {
    public Eagle(int location, int energy) {
        super(location, "Орел", "\uD83E\uDD85", energy);
    }
}
