package com.pixels.adapters;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.pixels.beans.Pixel;
import com.pixels.beans.User;

public class UserAdapter implements JsonSerializer<User> {

    Gson gson = new GsonBuilder().create();

    @Override
    public JsonElement serialize(User src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject json = new JsonObject();

        json.addProperty("user", src.getUsername());
        json.addProperty("balance", src.getBalance());

        List<Long> list = new ArrayList<>();
        for (Pixel pixel : src.getPixels()) {
            list.add(pixel.getId());
        }

        json.add("pixels", gson.toJsonTree(list));

        return json;
    }
}
