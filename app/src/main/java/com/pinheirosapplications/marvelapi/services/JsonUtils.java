package com.pinheirosapplications.marvelapi.services;

import com.google.gson.JsonObject;
import com.pinheirosapplications.marvelapi.models.Hero;
import com.pinheirosapplications.marvelapi.models.Thumbnail;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    public static Hero obtemHeros(JSONObject jsonObject) throws JSONException {
        Hero hero = new Hero();

        hero.setId(jsonObject.getInt("id"));
        hero.setName(jsonObject.getString("name"));
        hero.setDescription(jsonObject.getString("description"));
        hero.setModified(jsonObject.getString("modified"));
        hero.setThumbnail(new Thumbnail(jsonObject.getJSONObject("thumbnail").getString("path"), jsonObject.getJSONObject("thumbnail").getString("extension")));

        return hero;
    }

    @NotNull
    public static List<Hero> getHeroList(@Nullable String json) {
        List<Hero> listaDeHeros = new ArrayList<>();
        try {
            JSONObject jsonObj = new JSONObject(json);
            jsonObj = jsonObj.getJSONObject("data");
            JSONArray listHeros = jsonObj.getJSONArray("results");

            for(int i=0; i < listHeros.length(); i++){
                listaDeHeros.add(obtemHeros(listHeros.getJSONObject(i)));
            }
        }catch(JSONException e) {
            e.printStackTrace();
        }
        return listaDeHeros;
    }
}
