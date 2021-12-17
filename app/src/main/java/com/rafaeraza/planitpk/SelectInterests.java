package com.rafaeraza.planitpk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SelectInterests extends AppCompatActivity {

    CircleImageView imgCoastal, imgDeserts, imgForts, imgHillStations, imgIslands, imgLakes
            ,imgMines, imgMonuments, imgMosques, imgMountainous, imgMuseums, imgNationalParks
            ,imgTemples, imgValleys, imgWaterfalls;
    TextView txtCoastal, txtDeserts, txtForts, txtHillStations, txtIslands, txtLakes
            ,txtMines, txtMonuments, txtMosques, txtMountainous, txtMuseums, txtNationalParks
            ,txtTemples, txtValleys, txtWaterfalls;
    Button continueBtn;

    Animation fadeIn;

    private ArrayList<String> preferredCategories;

    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Locations");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_interests);

        //Animation
        fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in_slow);

        preferredCategories = new ArrayList<>();

        imgCoastal = findViewById(R.id.imgCoastal);
        txtCoastal = findViewById(R.id.txtCoastal);
        imgCoastal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredCategories.size() <= 14 && ! imgCoastal.isSelected())
                {
                    preferredCategories.add(txtCoastal.getText().toString());
                    imgCoastal.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_check_circle_outline_24));
                    imgCoastal.setAnimation(fadeIn);
                    txtCoastal.setTextColor(getResources().getColor(R.color.theme_green));
                    imgCoastal.setSelected(true);

                } else {
                    preferredCategories.remove(txtCoastal.getText().toString());
                    imgCoastal.setImageDrawable(getResources().getDrawable(R.drawable.nature));
                    txtCoastal.setTextColor(getResources().getColor(R.color.cardview_dark_background));
                    imgCoastal.setSelected(false);

                }
            }
        });

        imgDeserts = findViewById(R.id.imgDeserts);
        txtDeserts = findViewById(R.id.txtDeserts);
        imgDeserts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredCategories.size() <= 14 && ! imgDeserts.isSelected())
                {
                    preferredCategories.add(txtDeserts.getText().toString());
                    imgDeserts.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_check_circle_outline_24));
                    imgDeserts.setAnimation(fadeIn);
                    txtDeserts.setTextColor(getResources().getColor(R.color.theme_green));
                    imgDeserts.setSelected(true);

                } else {
                    preferredCategories.remove(txtDeserts.getText().toString());
                    imgDeserts.setImageDrawable(getResources().getDrawable(R.drawable.nature));
                    txtDeserts.setTextColor(getResources().getColor(R.color.cardview_dark_background));
                    imgDeserts.setSelected(false);
                }
            }
        });

        imgForts = findViewById(R.id.imgForts);
        txtForts = findViewById(R.id.txtForts);
        imgForts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredCategories.size() <= 14 && ! imgForts.isSelected())
                {
                    preferredCategories.add(txtForts.getText().toString());
                    imgForts.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_check_circle_outline_24));
                    imgForts.setAnimation(fadeIn);
                    txtForts.setTextColor(getResources().getColor(R.color.theme_green));
                    imgForts.setSelected(true);

                } else {
                    preferredCategories.remove(txtForts.getText().toString());
                    imgForts.setImageDrawable(getResources().getDrawable(R.drawable.nature));
                    txtForts.setTextColor(getResources().getColor(R.color.cardview_dark_background));
                    imgForts.setSelected(false);

                }
            }
        });

        imgHillStations = findViewById(R.id.imgHillStations);
        txtHillStations = findViewById(R.id.txtHillStations);
        imgHillStations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredCategories.size() <= 14 && ! imgHillStations.isSelected())
                {
                    preferredCategories.add(txtHillStations.getText().toString());
                    imgHillStations.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_check_circle_outline_24));
                    imgHillStations.setAnimation(fadeIn);
                    txtHillStations.setTextColor(getResources().getColor(R.color.theme_green));
                    imgHillStations.setSelected(true);

                } else {
                    preferredCategories.remove(txtHillStations.getText().toString());
                    imgHillStations.setImageDrawable(getResources().getDrawable(R.drawable.nature));
                    txtHillStations.setTextColor(getResources().getColor(R.color.cardview_dark_background));
                    imgHillStations.setSelected(false);

                }
            }
        });

        imgIslands = findViewById(R.id.imgIslands);
        txtIslands = findViewById(R.id.txtIslands);
        imgIslands.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredCategories.size() <= 14 && ! imgIslands.isSelected())
                {
                    preferredCategories.add(txtIslands.getText().toString());
                    imgIslands.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_check_circle_outline_24));
                    imgIslands.setAnimation(fadeIn);
                    txtIslands.setTextColor(getResources().getColor(R.color.theme_green));
                    imgIslands.setSelected(true);

                } else {
                    preferredCategories.remove(txtIslands.getText().toString());
                    imgIslands.setImageDrawable(getResources().getDrawable(R.drawable.nature));
                    txtIslands.setTextColor(getResources().getColor(R.color.cardview_dark_background));
                    imgIslands.setSelected(false);

                }
            }
        });

        imgLakes = findViewById(R.id.imgLakes);
        txtLakes = findViewById(R.id.txtLakes);
        imgLakes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredCategories.size() <= 14 && ! imgLakes.isSelected())
                {
                    preferredCategories.add(txtLakes.getText().toString());
                    imgLakes.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_check_circle_outline_24));
                    imgLakes.setAnimation(fadeIn);
                    txtLakes.setTextColor(getResources().getColor(R.color.theme_green));
                    imgLakes.setSelected(true);

                } else {
                    preferredCategories.remove(txtLakes.getText().toString());
                    imgLakes.setImageDrawable(getResources().getDrawable(R.drawable.nature));
                    txtLakes.setTextColor(getResources().getColor(R.color.cardview_dark_background));
                    imgLakes.setSelected(false);

                }
            }
        });

        imgMines = findViewById(R.id.imgMines);
        txtMines = findViewById(R.id.txtMines);
        imgMines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredCategories.size() <= 14 && ! imgMines.isSelected())
                {
                    preferredCategories.add(txtMines.getText().toString());
                    imgMines.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_check_circle_outline_24));
                    imgMines.setAnimation(fadeIn);
                    txtMines.setTextColor(getResources().getColor(R.color.theme_green));
                    imgMines.setSelected(true);

                } else {
                    preferredCategories.remove(txtMines.getText().toString());
                    imgMines.setImageDrawable(getResources().getDrawable(R.drawable.nature));
                    txtMines.setTextColor(getResources().getColor(R.color.cardview_dark_background));
                    imgMines.setSelected(false);

                }
            }
        });

        imgMonuments = findViewById(R.id.imgMonuments);
        txtMonuments = findViewById(R.id.txtMonuments);
        imgMonuments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredCategories.size() <= 14 && ! imgMonuments.isSelected())
                {
                    preferredCategories.add(txtMonuments.getText().toString());
                    imgMonuments.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_check_circle_outline_24));
                    imgMonuments.setAnimation(fadeIn);
                    txtMonuments.setTextColor(getResources().getColor(R.color.theme_green));
                    imgMonuments.setSelected(true);

                } else {
                    preferredCategories.remove(txtMonuments.getText().toString());
                    imgMonuments.setImageDrawable(getResources().getDrawable(R.drawable.nature));
                    txtMonuments.setTextColor(getResources().getColor(R.color.cardview_dark_background));
                    imgMonuments.setSelected(false);

                }
            }
        });

        imgMosques= findViewById(R.id.imgMosques);
        txtMosques = findViewById(R.id.txtMosques);
        imgMosques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredCategories.size() <= 14 && ! imgMosques.isSelected())
                {
                    preferredCategories.add(txtMosques.getText().toString());
                    imgMosques.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_check_circle_outline_24));
                    imgMosques.setAnimation(fadeIn);
                    txtMosques.setTextColor(getResources().getColor(R.color.theme_green));
                    imgMosques.setSelected(true);

                } else {
                    preferredCategories.remove(txtMosques.getText().toString());
                    imgMosques.setImageDrawable(getResources().getDrawable(R.drawable.nature));
                    txtMosques.setTextColor(getResources().getColor(R.color.cardview_dark_background));
                    imgMosques.setSelected(false);

                }
            }
        });

        imgMountainous= findViewById(R.id.imgMountainous);
        txtMountainous = findViewById(R.id.txtMountainous);
        imgMountainous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredCategories.size() <= 14 && ! imgMountainous.isSelected())
                {
                    preferredCategories.add(txtMountainous.getText().toString());
                    imgMountainous.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_check_circle_outline_24));
                    imgMountainous.setAnimation(fadeIn);
                    txtMountainous.setTextColor(getResources().getColor(R.color.theme_green));
                    imgMountainous.setSelected(true);

                } else {
                    preferredCategories.remove(txtMountainous.getText().toString());
                    imgMountainous.setImageDrawable(getResources().getDrawable(R.drawable.nature));
                    txtMountainous.setTextColor(getResources().getColor(R.color.cardview_dark_background));
                    imgMountainous.setSelected(false);

                }
            }
        });

        imgMuseums= findViewById(R.id.imgMuseums);
        txtMuseums = findViewById(R.id.txtMuseums);
        imgMuseums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredCategories.size() <= 14 && ! imgMuseums.isSelected())
                {
                    preferredCategories.add(txtMuseums.getText().toString());
                    imgMuseums.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_check_circle_outline_24));
                    imgMuseums.setAnimation(fadeIn);
                    txtMuseums.setTextColor(getResources().getColor(R.color.theme_green));
                    imgMuseums.setSelected(true);

                } else {
                    preferredCategories.remove(txtMuseums.getText().toString());
                    imgMuseums.setImageDrawable(getResources().getDrawable(R.drawable.nature));
                    txtMuseums.setTextColor(getResources().getColor(R.color.cardview_dark_background));
                    imgMuseums.setSelected(false);

                }
            }
        });

        imgNationalParks= findViewById(R.id.imgNationalParks);
        txtNationalParks = findViewById(R.id.txtNationalParks);
        imgNationalParks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredCategories.size() <= 14 && ! imgNationalParks.isSelected())
                {
                    preferredCategories.add(txtNationalParks.getText().toString());
                    imgNationalParks.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_check_circle_outline_24));
                    imgNationalParks.setAnimation(fadeIn);
                    txtNationalParks.setTextColor(getResources().getColor(R.color.theme_green));
                    imgNationalParks.setSelected(true);

                } else {
                    preferredCategories.remove(txtNationalParks.getText().toString());
                    imgNationalParks.setImageDrawable(getResources().getDrawable(R.drawable.nature));
                    txtNationalParks.setTextColor(getResources().getColor(R.color.cardview_dark_background));
                    imgNationalParks.setSelected(false);

                }
            }
        });

        continueBtn = findViewById(R.id.continueBtn);
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SelectInterests.this, SelectPreferences.class)
                .putExtra("preferred_categories",preferredCategories));
                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
            }
        });
    }

    public void skip(View view) {
        startActivity(new Intent(this, Explore.class));
        finish();
    }
}