public class Herbs extends Plant {
    public Herbs(int location, String name, String icon, int energy) {
        super(location, name, icon, energy);
    }
}

class Rice extends Herbs {
    public Rice(int location) {
        super(location, "Рис", "\uD83C\uDF3E", 3);
    }
}

class Grass extends Herbs {
    public Grass(int location) {
        super(location, "Трава", "\uD83C\uDF3F", 1);
    }
}

class Clover extends Herbs {
    public Clover(int location) {
        super(location, "Клевер", "\uD83C\uDF40", 2);
    }
}
