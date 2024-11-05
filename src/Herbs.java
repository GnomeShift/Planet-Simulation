public class Herbs extends Plant {

    public Herbs(int location, String name, String icon) {
        super(location, name, icon);
    }
}

class Rice extends Herbs {
    public Rice(int location) {
        super(location, "Рис", "\uD83C\uDF3E");
    }
}

class Grass extends Herbs {
    public Grass(int location) {
        super(location, "Трава", "\uD83C\uDF3F");
    }
}

class Clover extends Herbs {
    public Clover(int location) {
        super(location, "Клевер", "\uD83C\uDF40");
    }
}
