package com.example.pokedex;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewListAdapter.onNoteListener {
    private RecyclerView recyclerView;
    private ArrayList<Pokemon.PokemonItem> pokemons;
    private ArrayList<Pokemon.PokemonItem> pokemonsChanging;
    private RecyclerViewListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (findViewById(R.id.RecyclerView));

        Pokemon.getPokemon(MainActivity.this);
        mAdapter = new RecyclerViewListAdapter(Pokemon.data,MainActivity.this, this);
        pokemons = Pokemon.data;
        pokemonsChanging = Pokemon.data;
        recyclerView.setAdapter(mAdapter);
        final LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        final GridLayoutManager gridLayout = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(linearLayout);

        final Button switchButton = (Button) findViewById(R.id.switchButton);
        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchButton.getText().equals("Grid")){
                    switchButton.setText("List");
                    recyclerView.setLayoutManager(gridLayout);
                }
                else{
                    switchButton.setText("Grid");
                    recyclerView.setLayoutManager(linearLayout);
                }

            }
        });


        Button filterButton = (Button) findViewById(R.id.filterButton);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent filterIntent = new Intent(getApplicationContext(),FIlterActivity.class);
                startActivityForResult(filterIntent,1);
            }
        });



        SearchView search = (SearchView) findViewById(R.id.search);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchFilter(newText);
                mAdapter.filterList(pokemonsChanging);
                return false;
            }
        });

    }
    public void searchFilter(String s){
        ArrayList<Pokemon.PokemonItem> newPokemon = new ArrayList<>();
            for (Pokemon.PokemonItem pokemon : Pokemon.data){
                if (pokemon.name.toLowerCase().contains(s.toLowerCase()))
                    newPokemon.add(pokemon);
            }

        pokemonsChanging = newPokemon;
    }

    /**
     *
     * @param requestCode
     * @param resultCode
     * @param data
     *
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK){
            Bundle filterValues = data.getExtras();
            ArrayList<String> types = filterValues.getStringArrayList("types");
            String minHealth = filterValues.getString("minHealth");
            String minAttack = filterValues.getString("minAttack");
            String minDefense = filterValues.getString("minDefense");

            ArrayList<Pokemon.PokemonItem> newPokemon = new ArrayList<>();
            for(Pokemon.PokemonItem pokemon : Pokemon.data)
            {

                if (types.size() == 0)
                {
                    if (minHealth.equals("") || Integer.parseInt(minHealth) < Integer.parseInt(pokemon.hp)){
                        if (minAttack.equals("") || Integer.parseInt(minAttack) < Integer.parseInt(pokemon.attack)){
                            if (minDefense.equals("") || Integer.parseInt(minDefense) < Integer.parseInt(pokemon.defense)){
                                newPokemon.add(pokemon);
                            }
                        }
                    }
                }
                else
                {
                    for (String fType : types){
                        if (pokemon.type.toLowerCase().contains(fType.toLowerCase())){
                            if (minHealth.equals("") || Integer.parseInt(minHealth) < Integer.parseInt(pokemon.hp)){
                                if (minAttack.equals("") || Integer.parseInt(minAttack) < Integer.parseInt(pokemon.attack)){
                                    if (minDefense.equals("") || Integer.parseInt(minDefense) < Integer.parseInt(pokemon.defense)){
                                        if(!newPokemon.contains(pokemon)){
                                            newPokemon.add(pokemon);
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
            }
            pokemonsChanging = newPokemon;
            mAdapter.filterList(pokemonsChanging);

        }
    }

    @Override
    public void onNoteClick(int position) {


        Intent intent = new Intent(this, DetailedPokemon.class);
        intent.putExtra("pokemonObject", (Parcelable) pokemonsChanging.get(position));
        startActivity(intent);
    }
}



