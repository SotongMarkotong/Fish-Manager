package persistence;


import model.Fish;

import static org.junit.jupiter.api.Assertions.assertEquals;

// REFERENCED FROM JsonSerializationDemo

public class JsonTest {
    protected void checkFish(String species, int weight, Fish fish) {
        assertEquals(species, fish.getSpecies());
        assertEquals(weight, fish.getWeight());
    }
}
