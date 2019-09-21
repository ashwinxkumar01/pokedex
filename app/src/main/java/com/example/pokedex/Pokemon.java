package com.example.pokedex;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import java.util.ArrayList;
import java.util.Iterator;

public class Pokemon {
    public static ArrayList<PokemonItem> data;
    public static void getPokemon(Context context)
    {
        ArrayList<PokemonItem> pokemons = new ArrayList<>();
        try {
            String pokedata = loadJSONFromAsset(context);
            JSONObject jObj = new JSONObject(pokedata);
            Iterator<String> iterator = jObj.keys();
            while (iterator.hasNext()) {
                String name = iterator.next();
                    pokemons.add(new PokemonItem(name, jObj.getJSONObject(name)));
            }
        } catch (JSONException a) {
            Log.i("Error", "Error");
        }
        data = pokemons;
    }

    public static class PokemonItem{
        String name,number,attack,defense,hp,species,speed,total,type;
        public PokemonItem(String title, JSONObject data){
            try {
                name = title;
                number = data.getString("#").trim();
                attack = data.getString("Attack").trim();
                defense = data.getString("Defense").trim();
                hp = data.getString("HP").trim();
                species = data.getString("Species").trim();
                speed = data.getString("Speed").trim();
                total = data.getString("Total").trim();
                type = data.getString("Type").trim();
            }
            catch (JSONException a){
                Log.i("Error", "Error");
            }



        }
    }
    public static String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("pokeData.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}



