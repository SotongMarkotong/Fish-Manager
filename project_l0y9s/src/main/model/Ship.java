package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// represents a ship having name and list of caught fish.
public class Ship implements Writable {
    private static final int MAX = 60000; // the max currentWeight a ship can achieve
    private static final String EV1 = "Added a fish of species: ";
    private static final String EV2 = " with weight(kg) of ";
    private String name;
    private int currentWeight;
    private List<Fish> caughtFish;

    // EFFECTS: constructs a ship with given name and starting currentWeight(kg) of 0 with an empty list of caught fish.
    public Ship(String name) {
        this.name = name;
        this.currentWeight = 0;
        this.caughtFish = new ArrayList<>();
    }

    // REQUIRES: Fish must be instantiated
    // MODIFIES: this.
    // EFFECTS: add a fish that has just been caught into caughtList.
    public void addAFish(Fish fish) {
        this.caughtFish.add(fish);
        this.currentWeight = currentWeight + fish.getWeight();
        EventLog.getInstance().logEvent(new Event(EV1 + fish.getSpecies() + EV2 + fish.getWeight()));
    }

    // MODIFIES: this.
    // EFFECTS: removes all fish from the caughtList.
    public void removeAllFish() {
        this.caughtFish.clear();
        EventLog.getInstance().logEvent(new Event("Removed all fish from ship!"));
    }

    // REQUIRES: Fish must be instantiated
    // MODIFIES: this.
    // EFFECTS: produces true if the ship's current weight(in kg) doesn't exceed MAX when a new fish is added.
    public boolean addWeight(Fish fish) {
        return this.currentWeight + fish.getWeight() <= MAX;
    }

    // MODIFIES: this.
    // EFFECTS: removes all the currentWeight of the ship
    public void removeAllWeight() {
        this.currentWeight = 0;
        EventLog.getInstance().logEvent(new Event("Removed all weight from ship!"));
    }

    // EFFECTS: returns the name of the ship
    public String getName() {
        return name;
    }

    // EFFECTS: returns the current total weight of the ship
    public int getCurrentWeight() {
        return currentWeight;
    }

    // EFFECTS: returns an unmodifiable list of fish in the ship
    public List<Fish> getCaughtFish() {
        return Collections.unmodifiableList(caughtFish);
    }

    // MODIFIES: this.
    // EFFECTS: returns a list containing the species of the fish that has been already caught
    public List getSpeciesCaughtFish() {
        ArrayList<String> result = new ArrayList<>();
        for (Fish f : caughtFish) {
            result.add(f.getSpecies());
        }
        return result;
    }

    // REFERENCED FROM JsonSerializationDemo

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("currentWeight", currentWeight);
        json.put("caughtFish", caughtFishToJson());
        return json;
    }

    // EFFECTS: returns caught fish in this workroom as a JSON array
    private JSONArray caughtFishToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Fish f : caughtFish) {
            jsonArray.put(f.toJson());
        }

        return jsonArray;
    }
}

