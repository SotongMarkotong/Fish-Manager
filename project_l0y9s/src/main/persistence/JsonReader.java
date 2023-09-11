package persistence;

import model.Fish;
import model.Ship;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// REFERENCED FROM JsonSerializationDemo

// Represents a reader that reads ship from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads ship from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Ship read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseShip(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses ship from JSON object and returns it
    private Ship parseShip(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Ship s = new Ship(name);
        addCaughtFish(s, jsonObject);
        return s;
    }

    // MODIFIES: s
    // EFFECTS: parses list of fish from JSON object and adds them to ship
    private void addCaughtFish(Ship s, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("caughtFish");
        for (Object json : jsonArray) {
            JSONObject nextFish = (JSONObject) json;
            addAFish(s, nextFish);
        }
    }

    // MODIFIES: s
    // EFFECTS: parses fish from JSON object and adds it to ship
    private void addAFish(Ship s, JSONObject jsonObject) {
        String species = jsonObject.getString("species");
        int weight = jsonObject.getInt("weight");
        Fish fish = new Fish(species, weight);
        s.addAFish(fish);
    }
}
