package com.pixels.adapters;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.pixels.beans.Pixel;
import com.pixels.beans.User;

public class PixelAdapter implements JsonSerializer<Pixel> {

    @Override
    public JsonElement serialize(Pixel src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject json = new JsonObject();

        json.addProperty("id", src.getId());
        json.addProperty("price", src.getPrice());
        json.addProperty("color", src.getColor());
        json.addProperty("description", src.getDescription());

        User owner = src.getOwner();
        if (owner != null) {
            json.addProperty("owner_username", owner.getUsername());
        }

        return json;
    }
}
