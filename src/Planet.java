import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Planet {
    public List<Cell> cells;
    private Random random = new Random();
    private final ExecutorService executor;
    private final AtomicInteger dayCounter;

    public Planet(int size) {
        cells = new ArrayList<>();
        this.dayCounter = new AtomicInteger(0);
        this.executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (int i = 0; i < size; i++) {
            cells.add(new Cell(i, this));
        }
    }

    public void terraform(Animal[] animals, Plant[] plants, int days) {
        for (Cell cell : cells) {
            cell.addAnimal(animals[random.nextInt(animals.length)]);
            cell.addPlant(plants[random.nextInt(plants.length)]);
        }

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable dailyTask = () -> {
            int currentDay = dayCounter.incrementAndGet();
            System.out.println("\nДЕНЬ " + currentDay + ":");
            try {
                simulateDay();
            }
            catch (RejectedExecutionException e) {
                System.err.println("Ошибка: Пул потоков перегружен!");
            }
        };

        scheduler.scheduleAtFixedRate(dailyTask, 0, 1, TimeUnit.SECONDS);

        try {
            Thread.sleep(days * 1000L);
            scheduler.shutdown();
            if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
            executor.shutdown();
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void simulateDay() {
        executor.submit(() -> {
            List<String> dayEvents = new ArrayList<>();

            for (Cell cell : cells) {
                dayEvents.addAll(cell.moveAnimals());
                dayEvents.addAll(cell.multiplyAnimals());
                dayEvents.addAll(cell.interact());
            }

            System.out.println("СОБЫТИЯ ДНЯ:");
            if (dayEvents.isEmpty()) {
                System.out.println("Ничего интересного...");
            }
            else {
                for (String event : dayEvents) {
                    System.out.println(event);
                }
            }

            for (Cell cell : cells) {
                System.out.println(cell);
            }
        });
    }
}

class Cell {
    public int location;
    public List<Animal> animals = new ArrayList<>();
    private List<Plant> plants = new ArrayList<>();
    public Planet planet;

    public Cell(int location, Planet planet) {
        this.location = location;
        this.planet = planet;
    }

    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public synchronized List<String> moveAnimals() {
        List<String> events = new ArrayList<>();
        List<Animal> diedAnimals = new ArrayList<>();
        List<Animal> movedAnimals = new ArrayList<>();
        List<Animal> animalsCopy = new ArrayList<>(animals);

        for (Animal animal : animalsCopy) {
            if (animal.isAlive()) {
                int newLocation = animal.move(this);
                if (newLocation != animal.getLocation()) {
                    movedAnimals.add(animal);
                }
                else if (animal.getEnergy() <= 0) {
                    diedAnimals.add(animal);
                }
            }
        }

        for (Animal animal : movedAnimals) {
            animals.remove(animal);
            if (animal.getLocation() >= 0 && animal.getLocation() < planet.cells.size()) {
                planet.cells.get(animal.getLocation()).addAnimal(animal);
            }
            else {
                events.add("Ошибка: " + animal.getName() + " выпало за уровень");
                animal.die();
            }
        }

        for (Animal animal : diedAnimals) {
            events.add(animal.getName() + " умер(-ла) в клетке " + animal.getLocation() + " от истощения");
            animal.die();
        }
        return events;
    }

    public synchronized List<String> multiplyAnimals() {
        List<Animal> newAnimals = new ArrayList<>();
        List<String> events = new ArrayList<>();
        List<Animal> animalsCopy = new ArrayList<>(animals);

        for (Animal animal : animalsCopy) {
            if (animal.isAlive() && animal.canMultiply()) {
                Animal newAnimal = animal.multiply();
                if (newAnimal != null) {
                    newAnimals.add(newAnimal);
                    events.add(animal.getName() + " размножился(-лась) в клетке " + location);
                }
            }
        }
        for (Animal animal : newAnimals) {
            animals.add(animal);

            if (animal.getLocation() >= 0 && animal.getLocation() < planet.cells.size()){
                planet.cells.get(animal.getLocation()).addAnimal(animal);
            }
            else {
                events.add("Ошибка: " + animal.getName() + " появилось за пределами планеты");
                animal.die();
            }
        }
        return events;
    }

    public synchronized List<String> interact() {
        List<String> events = new ArrayList<>();
        List<Animal> animalsCopy = new ArrayList<>(animals);

        for (Animal animal1 : animalsCopy) {
            if (animal1.isAlive()) {
                for (Animal animal2 : animalsCopy) {
                    if (animal1 != animal2 && animal2.isAlive() && Math.abs(animal1.getLocation() - animal2.getLocation()) <= 1) {
                        events.addAll(animal1.interact(animal2));
                    }
                }
                for (Plant plant : plants) {
                    events.addAll(animal1.interact(plant));
                }
            }
        }

        return events;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Клетка " + location + ": ");
        if (!plants.isEmpty()) {
            for (Plant plant : plants) {
                sb.append(plant.getName()).append(plant.getIcon()).append(plant.isAlive() ? "" : "(\uD83D\uDC80)").append(" ");
            }
        }
        if (!animals.isEmpty()) {
            for (Animal animal : animals) {
                sb.append(animal.getName()).append(animal.getIcon());
                if (animal.isAlive()) {
                    sb.append("(").append(animal.getEnergy()).append("⚡️)");
                }
                else {
                    sb.append("(\uD83D\uDC80)");
                }
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
