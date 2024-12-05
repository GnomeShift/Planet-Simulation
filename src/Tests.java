import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {
    private final int size = 2;
    private final Planet testPlanet = new Planet(size);
    private Cell cell;

    @BeforeEach
    public void setUp() {
        cell = new Cell(size, testPlanet);
    }

    @Test
    public void testCellCreation() {
        Assertions.assertTrue(cell.animals.isEmpty(), "Список животных изначально должен быть пустой");
    }

    @Test
    public void testHunt() {
        Animal duck = new Duck(1, 10);
        Animal caterpillar = new Caterpillar(1, 5);

        cell.addAnimal(duck);
        cell.addAnimal(caterpillar);

        Assertions.assertTrue(duck.canHunt(caterpillar), "Утка должна охотиться на гусеницу");
    }

    @Test
    public void testDead() {
        Animal duck = new Duck(1, 10);

        cell.addAnimal(duck);
        duck.die();

        Assertions.assertFalse(duck.isAlive(), "Утка должна быть мертва");
    }

    @Test
    public void testPlantInteraction() {
        Animal duck = new Duck(1, 10);
        Plant grass = new Grass(1);

        cell.addAnimal(duck);
        cell.addPlant(grass);
        List<String> interactions = duck.interact(grass);

        assertEquals(1, interactions.size());
        assertEquals("Утка съел(-а) Трава в клетке 1 (+1⚡️)", interactions.getFirst());
    }

    @Test
    public void testPredatorHunt() {
        Snake snake = new Snake(1, 10);
        Fox fox = new Fox(1, 8);

        cell.addAnimal(snake);
        cell.addAnimal(fox);

        Assertions.assertFalse(snake.canHunt(fox), "Змея не должна охотиться на лису");
    }

    @Test
    public void testPredatorPlantInteraction() {
        Snake snake = new Snake(1, 10);
        Grass grass = new Grass(1);

        cell.addAnimal(snake);
        cell.addPlant(grass);

        Assertions.assertTrue(snake.interact(grass).isEmpty(), "Змея не должна есть растения");
    }
}
