package com.example.pokedex;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerViewListAdapter extends RecyclerView.Adapter<RecyclerViewListAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewListAdapter";

    private ArrayList<Pokemon.PokemonItem> pokemonNames;
    private Context mContext;
    private onNoteListener onNoteListener;


    public RecyclerViewListAdapter(ArrayList<Pokemon.PokemonItem> pokemonNames, Context mContext, onNoteListener onNoteListener) {
        this.pokemonNames = pokemonNames;
        this.mContext = mContext;
        this.onNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_list_icon, parent, false);
        ViewHolder holder = new ViewHolder(view, onNoteListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pokemon.PokemonItem currentPokemon = pokemonNames.get(position);
        String pokemonUrl = "https://img.pokemondb.net/artwork/" + currentPokemon.name.toLowerCase() + ".jpg";
        Glide.with(mContext).load("https://www.sccpre.cat/mypng/detail/444-4449454_pokeball-pokemon-ball-transparent-background.png").into(holder.pokemonIcon);
        if (Patterns.WEB_URL.matcher(pokemonUrl).matches()){
            Glide.with(mContext).load(pokemonUrl).into(holder.pokemonIcon);
        }
        holder.pokemonName.setText(currentPokemon.name);
    }

    @Override
    public int getItemCount() {
        if (pokemonNames != null){
            return pokemonNames.size();

        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        ImageView pokemonIcon;
        TextView pokemonName;
        RelativeLayout parentLayout;
        Context context;
        onNoteListener onNoteListener;

        public ViewHolder(@NonNull View itemView, onNoteListener onNoteListener) {
            super(itemView);
            context = itemView.getContext();

            pokemonIcon = itemView.findViewById(R.id.pokemonIcon);
            pokemonName = itemView.findViewById(R.id.pokemonName);
            parentLayout = itemView.findViewById(R.id.parentLayout);
            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());

        }
    }

    public void filterList(ArrayList<Pokemon.PokemonItem> filteredList){
        pokemonNames = filteredList;
        notifyDataSetChanged();


    }

    public interface onNoteListener{
        void onNoteClick(int position);
    }
}
