package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ShipTest{

    private Ship ship;
    private Fish f1;
    private Fish f2;
    private Fish f3;

    @BeforeEach
    public void runBefore() {
        this.ship = new Ship("Serba Prima");
        f1 = new Fish ("Bluefin Tuna", 200);
        f2 = new Fish ("Swordfish", 100);
        f3 = new Fish ("A big Whale", 69000);
    }

    @Test
    public void testConstructor(){
        Ship kapal = new Ship("Stanley");
        Ship boat = new Ship("Michael");
        assertEquals("Serba Prima", this.ship.getName());
        assertEquals(0, this.ship.getCurrentWeight());
        assertEquals(0, kapal.getCurrentWeight());
        assertEquals(0, boat.getCurrentWeight());
        assertTrue(this.ship.getSpeciesCaughtFish().isEmpty());
        this.ship.addAFish(f1);
        assertFalse(this.ship.getSpeciesCaughtFish().isEmpty());
    }

    @Test
    public void testAddAFish(){
        ship.addAFish(f1);
        assertTrue(ship.getSpeciesCaughtFish().contains(f1.getSpecies()));
        assertEquals(200, ship.getCurrentWeight());
        ship.addAFish(f2);
        assertTrue(ship.getSpeciesCaughtFish().contains(f2.getSpecies()));
        assertEquals(300, ship.getCurrentWeight());
        assertFalse(ship.getSpeciesCaughtFish().contains(f3.getSpecies()));
    }

    @Test
    public void testRemoveAllFish() {
        ship.addAFish(f1);
        ship.addAFish(f2);
        assertFalse(ship.getSpeciesCaughtFish().isEmpty());
        ship.removeAllFish();
        assertTrue(ship.getSpeciesCaughtFish().isEmpty());
    }

    @Test
    public void testAddWeight(){
        assertTrue(ship.addWeight(f1));
        assertTrue(ship.addWeight(f2));
        assertFalse(ship.addWeight(f3));
    }

    @Test
    public void testRemoveAllWeight() {
        ship.addAFish(f1);
        ship.removeAllWeight();
        assertEquals(0, ship.getCurrentWeight());
        ship.addAFish(f2);
        ship.removeAllWeight();
        assertEquals(0, ship.getCurrentWeight());
    }

    @Test
    public void testGetSpeciesCaughtFish() {
        ship.addAFish(f1);
        assertEquals(Arrays.asList("Bluefin Tuna"), ship.getSpeciesCaughtFish());
        ship.addAFish(f2);
        assertEquals(Arrays.asList("Bluefin Tuna", "Swordfish"), ship.getSpeciesCaughtFish());
    }
}
