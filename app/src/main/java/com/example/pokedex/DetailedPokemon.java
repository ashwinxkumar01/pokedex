package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

public class DetailedPokemon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_pokemon);

        Intent intent = getIntent();
        Pokemon.PokemonItem pokemon = intent.getParcelableExtra("pokemonObject");
        ImageView pokemonImage = findViewById(R.id.pokemonImage);
        TextView pokemonName = findViewById(R.id.pokemonName);
        TextView hp = findViewById(R.id.hp);
        TextView type = findViewById(R.id.type);
        TextView species = findViewById(R.id.species);
        TextView attack = findViewById(R.id.attack);
        TextView specialAttack = findViewById(R.id.spAtck);
        TextView specialDefense = findViewById(R.id.spDefnse);
        TextView defense = findViewById(R.id.defense);
        TextView speed = findViewById(R.id.speed);
        TextView total = findViewById(R.id.total);
        TextView number = findViewById(R.id.number);

        String pokemonUrl = "https://img.pokemondb.net/artwork/" + pokemon.name.toLowerCase() + ".jpg";
        Glide.with(DetailedPokemon.this).load("https://www.sccpre.cat/mypng/detail/444-4449454_pokeball-pokemon-ball-transparent-background.png").into(pokemonImage);
        if (Patterns.WEB_URL.matcher(pokemonUrl).matches()){
            Glide.with(DetailedPokemon.this).load(pokemonUrl).into(pokemonImage);
        }
        pokemonName.setText(pokemon.name);
        hp.setText("HP: " + pokemon.hp);
        type.setText("Type(s): " + pokemon.type);
        if (!pokemon.species.equals("")){
            species.setText("Species: " + pokemon.species);
        }
        attack.setText("Attack: " + pokemon.attack);
        if (pokemon.specialAttack != null){
            specialAttack.setText("Special:" + pokemon.specialAttack);
        }
        if (pokemon.specialDefense != null) {
            specialAttack.setText("Special:" + pokemon.specialAttack);
        }
        defense.setText("Defense: " + pokemon.defense);
        speed.setText("Speed: " + pokemon.speed);
        total.setText("Total: " + pokemon.total);
        number.setText("#: " + pokemon.number);



    }
}
