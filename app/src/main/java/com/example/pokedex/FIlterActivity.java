package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;

public class FIlterActivity extends AppCompatActivity {


    ArrayList<String> types = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);


        Button returnButton = (Button) findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText minHealth = (EditText) findViewById(R.id.minHealth);
                EditText minAttack = (EditText) findViewById(R.id.minAttack);
                EditText minDefense = (EditText) findViewById(R.id.minDefense);
                String mHealth = minHealth.getText().toString();
                String mAttack = minAttack.getText().toString();
                String mDefense = minDefense.getText().toString();
                Bundle extras = new Bundle();
                extras.putStringArrayList("types",types);
                extras.putString("minDefense",mDefense);
                extras.putString("minAttack",mAttack);
                extras.putString("minHealth",mHealth);
                Intent retFilters = new Intent();
                retFilters.putExtras(extras);
                setResult(RESULT_OK,retFilters);
                finish();

            }
        });



    }

    public void onCheckboxClicked(View view){
        boolean isChecked = ((CheckBox) view).isChecked();
        switch (view.getId())
        {
            case R.id.grass:
                if (isChecked)
                {
                    types.add("Grass");
                }
                else{
                    types.remove("Grass");
                }
                break;
            case R.id.water:
                if (isChecked)
                {
                    types.add("Water");
                }
                else{
                    types.remove("Water");
                }
                break;
            case R.id.fire:
                if (isChecked)
                {
                    types.add("Fire");
                }
                else{
                    types.remove("Fire");
                }
                break;
            case R.id.electric:
                if (isChecked)
                {
                    types.add("Electric");
                }
                else{
                    types.remove("Electric");
                }
                break;
            case R.id.ground:
                if (isChecked)
                {
                    types.add("Ground");
                }
                else{
                    types.remove("Ground");
                }
                break;
            case R.id.ice:
                if (isChecked)
                {
                    types.add("Ice");
                }
                else{
                    types.remove("Ice");
                }
                break;
            case R.id.flying:
                if (isChecked)
                {
                    types.add("Flying");
                }
                else{
                    types.remove("Flying");
                }
                break;
            case R.id.rock:
                if (isChecked)
                {
                    types.add("Rock");
                }
                else{
                    types.remove("Rock");
                }
                break;
            case R.id.steel:
                if (isChecked)
                {
                    types.add("Steel");
                }
                else{
                    types.remove("Steel");
                }
                break;
            case R.id.normal:
                if (isChecked)
                {
                    types.add("Normal");
                }
                else{
                    types.remove("Normal");
                }
                break;
            case R.id.fighting:
                if (isChecked)
                {
                    types.add("Fighting");
                }
                else{
                    types.remove("Fighting");
                }
                break;
            case R.id.ghost:
                if (isChecked)
                {
                    types.add("Ghost");
                }
                else{
                    types.remove("Ghost");
                }
                break;
            case R.id.dark:
                if (isChecked)
                {
                    types.add("Dark");
                }
                else{
                    types.remove("Dark");
                }
                break;
            case R.id.psychic:
                if (isChecked)
                {
                    types.add("Psychic");
                }
                else{
                    types.remove("Psychic");
                }
                break;
            case R.id.poison:
                if (isChecked)
                {
                    types.add("Poison");
                }
                else{
                    types.remove("Poison");
                }break;
            case R.id.dragon:
                if (isChecked)
                {
                    types.add("Dragon");
                }
                else{
                    types.remove("Dragon");
                }
                break;
            case R.id.fairy:
                if (isChecked)
                {
                    types.add("Fairy");
                }
                else{
                    types.remove("Fairy");
                }
                break;
            case R.id.bug:
                if (isChecked)
                {
                    types.add("Bug");
                }
                else{
                    types.remove("Bug");
                }
                break;

    }

    }
}
