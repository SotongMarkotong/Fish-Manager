package persistence;

import model.Fish;
import model.Ship;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// REFERENCED FROM JsonSerializationDemo

public class JsonWriterTest extends JsonTest {

    @Test
    public void testWriterInvalidFile() {
        try {
            Ship s = new Ship("Serba Prima");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyShip() {
        try {
            Ship s = new Ship("Serba Prima");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyShip.json");
            writer.open();
            writer.write(s);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyShip.json");
            s = reader.read();
            assertEquals("Serba Prima", s.getName());
            assertEquals(0, s.getCurrentWeight());
            assertEquals(0, s.getSpeciesCaughtFish().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralShip() {
        try {
            Ship s = new Ship("Serba Prima");
            s.addAFish(new Fish("Yellowfin Tuna", 45));
            s.addAFish(new Fish("BlueFin Tuna", 50));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralShip.json");
            writer.open();
            writer.write(s);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralShip.json");
            s = reader.read();
            assertEquals("Serba Prima", s.getName());
            assertEquals(95, s.getCurrentWeight());
            List<Fish> caughtFish = s.getCaughtFish();
            assertEquals(2, s.getCaughtFish().size());
            checkFish("Yellowfin Tuna", 45, caughtFish.get(0));
            checkFish("BlueFin Tuna", 50, caughtFish.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
