package model;


import org.json.JSONObject;
import persistence.Writable;

// represents a fish having species and weight
public class Fish implements Writable {
    private String species;
    private int weight;

    // EFFECTS: construct a fish with its species name and weight in kg
    public Fish(String species, int weight) {
        this.species = species;
        this.weight = weight;
    }

    // EFFECTS: returns species of the fish
    public String getSpecies() {
        return species;
    }

    // EFFECTS: returns weight of the fish
    public int getWeight() {
        return weight;
    }

    // REFERENCED FROM JsonSerializationDemo

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("species", species);
        json.put("weight", weight);
        return json;
    }
}

