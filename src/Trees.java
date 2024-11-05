public  class Trees extends Plant {

    public Trees(int location, String name, String icon) {
        super(location, name, icon);
    }
}

class Evergreen extends Trees {
    public Evergreen(int location) {
        super(location, "Елка", "\uD83C\uDF32");
    }
}

class Oak extends Trees {
    public Oak(int location) {
        super(location, "Дуб", "\uD83C\uDF33");
    }
}
