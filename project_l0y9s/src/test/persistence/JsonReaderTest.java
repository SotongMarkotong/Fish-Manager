package persistence;

import model.Fish;
import model.Ship;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// REFERENCED FROM JsonSerializationDemo

public class JsonReaderTest extends JsonTest {

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Ship s = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderEmptyShip() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyShip.json");
        try {
            Ship s = reader.read();
            assertEquals("Serba Prima", s.getName());
            assertEquals(0, s.getCurrentWeight());
            assertEquals(0, s.getCaughtFish().size());
        } catch (IOException e) {
                fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderGeneralShip() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralShip.json");
        try {
            Ship s = reader.read();
            assertEquals("Serba Prima", s.getName());
            List<Fish> caughtFish = s.getCaughtFish();
            assertEquals(2, caughtFish.size());
            checkFish("Yellowfin Tuna", 45, caughtFish.get(0));
            checkFish("BlueFin Tuna", 50, caughtFish.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

