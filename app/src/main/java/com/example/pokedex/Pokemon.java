package com.example.pokedex;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import java.util.ArrayList;
import java.util.Iterator;

public class Pokemon{
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

    public static class PokemonItem implements Parcelable{
        String name,number,attack,defense,hp,species,speed,total,type,specialAttack,specialDefense;
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
                specialDefense = data.getString("Sp. Def").trim();
                specialAttack = data.getString("Sp. Atk").trim();
            }
            catch (JSONException a){
                Log.i("Error", "Error");
            }



        }

        protected PokemonItem(Parcel in) {
            name = in.readString();
            number = in.readString();
            attack = in.readString();
            defense = in.readString();
            hp = in.readString();
            species = in.readString();
            speed = in.readString();
            total = in.readString();
            type = in.readString();
            specialAttack = in.readString();
            specialDefense = in.readString();
        }

        public static final Creator<PokemonItem> CREATOR = new Creator<PokemonItem>() {
            @Override
            public PokemonItem createFromParcel(Parcel in) {
                return new PokemonItem(in);
            }

            @Override
            public PokemonItem[] newArray(int size) {
                return new PokemonItem[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(name);
            dest.writeString(number);
            dest.writeString(attack);
            dest.writeString(defense);
            dest.writeString(hp);
            dest.writeString(species);
            dest.writeString(speed);
            dest.writeString(total);
            dest.writeString(type);
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



