package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class FishTest{

    private Fish fish;

    @BeforeEach
    public void runBefore(){
        this.fish = new Fish("Yellowfin Tuna", 45);
    }

    @Test
    public void testConstructor(){
        assertEquals("Yellowfin Tuna", this.fish.getSpecies());
        assertEquals(45, this.fish.getWeight());
    }
}