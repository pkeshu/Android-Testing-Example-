package com.keshar.androidtestingexample;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

public class JsonParser {
    private static Gson gson = new Gson();

    public static Location parseLocationFromJson(String json) {
        if (json == null || json.isEmpty()) {
            return new Location();
        }
        String cityName = null;
        double lat = 0.0;
        double lng = 0.0;
        try {
            JsonObject input = gson.fromJson(json, JsonObject.class);
            if (input.has("results") && input.size() > 0) {
                JsonArray results = input.getAsJsonArray("results");
                if (results != null && results.size() > 0) {
                    JsonObject firstObject = results.get(0).getAsJsonObject();
                    if (firstObject.has("formatted_address")) {
                        cityName = firstObject.get("formatted_address").getAsString();
                    }
                    if (firstObject.has("geometry")) {
                        JsonObject geometry = firstObject.get("geometry").getAsJsonObject();
                        if (geometry.has("location")) {
                            JsonObject location = geometry.get("location").getAsJsonObject();
                            if (location.has("lat"))
                                lat = location.get("lat").getAsDouble();
                            if (location.has("lng"))
                                lng = location.get("lng").getAsDouble();
                        }

                    }
                }


                return new Location(cityName, lat, lng);
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return new Location();

    }
}
