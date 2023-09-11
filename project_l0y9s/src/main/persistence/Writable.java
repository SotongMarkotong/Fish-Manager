package persistence;

import org.json.JSONObject;

// REFERENCED FROM JsonSerializationDemo

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
