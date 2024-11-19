class Herbivores extends Animal {
    public Herbivores(int location, String name, String icon, int energy) {
        super(location, name, icon, energy);
    }

    @Override
    public boolean canHunt(Animal other) {
        return false;
    }

    @Override
    public int hunt(Animal other) {
        return 0;
    }

    @Override
    public boolean canEat(Plant plant) {
        return plant instanceof Herbs;
    }

    @Override
    public int eat(Plant plant) {
        if(plant.isAlive() && canEat(plant)){
            int energyPlus = plant.getEnergy();
            plant.die();
            return energyPlus;
        }
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

class Horse extends Herbivores {
    public Horse(int location, int energy) {
        super(location, "Лошадь", "\uD83D\uDC0E", energy);
    }
}

class Deer extends Herbivores {
    public Deer(int location, int energy) {
        super(location, "Олень", "\uD83E\uDD8C", energy);
    }
}

class Rabbit extends Herbivores {
    public Rabbit(int location, int energy) {
        super(location, "Кролик", "\uD83D\uDC07", energy);
    }
}

class Mouse extends Herbivores {
    public Mouse(int location, int energy) {
        super(location, "Мышь", "\uD83D\uDC01", energy);
    }
}

class Goat extends Herbivores {
    public Goat(int location, int energy) {
        super(location, "Коза", "\uD83D\uDC10", energy);
    }
}

class Sheep extends Herbivores {
    public Sheep(int location, int energy) {
        super(location, "Овца", "\uD83D\uDC11", energy);
    }
}

class Boar extends Herbivores {
    public Boar(int location, int energy) {
        super(location, "Кабан", "\uD83D\uDC17", energy);
    }
}

class Buffalo extends Herbivores {
    public Buffalo(int location, int energy) {
        super(location, "Буйвол", "\uD83D\uDC03", energy);
    }
}

class Duck extends Herbivores {
    public Duck(int location, int energy) {
        super(location, "Утка", "\uD83E\uDD86", energy);
    }

    @Override
    public boolean canHunt(Animal other) {
        return other instanceof Caterpillar;
    }

    @Override
    public int hunt(Animal other) {
        return 2;
    }
}

class Caterpillar extends Herbivores {
    public Caterpillar(int location, int energy) {
        super(location, "Гусеница", "\uD83D\uDC1B", energy);
    }
}
