package com.rafaeraza.planitpk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class SelectInterests extends AppCompatActivity {

    /*
    Declarations
     */
    CircleImageView imgCoastal, imgDeserts, imgForts, imgHillStations, imgIslands, imgLakes,
            imgMines, imgMonuments, imgMosques, imgMountainous, imgMuseums, imgNationalParks,
            imgTemples, imgValleys, imgWaterfalls;
    TextView txtCoastal, txtDeserts, txtForts, txtHillStations, txtIslands, txtLakes, txtMines,
            txtMonuments, txtMosques, txtMountainous, txtMuseums, txtNationalParks, txtTemples,
            txtValleys, txtWaterfalls;
    Button continueBtn;

    Animation fadeIn;

    private ArrayList<String> preferredCategories;

    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Categories");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_interests);

        //Animation
        fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in_slow);

        preferredCategories = new ArrayList<>();

        /*
        Load and set category image from Firebase
         */
        imgCoastal = findViewById(R.id.imgCoastal);
        final String[] coastalImage = new String[1];
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (Objects.equals(dataSnapshot.child("name").getValue(String.class), "Coastal")) {
                        coastalImage[0] = dataSnapshot.child("image").getValue(String.class);
                        Glide.with(SelectInterests.this)
                                .load(coastalImage[0])
                                .centerCrop()
                                .into(imgCoastal);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        txtCoastal = findViewById(R.id.txtCoastal);
        imgCoastal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredCategories.size() <= 14 && !imgCoastal.isSelected()) {
                    preferredCategories.add(txtCoastal.getText().toString());
                    imgCoastal.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_check_circle_outline_24));
                    imgCoastal.setAnimation(fadeIn);
                    txtCoastal.setTextColor(getResources().getColor(R.color.theme_green));
                    imgCoastal.setSelected(true);

                } else {
                    preferredCategories.remove(txtCoastal.getText().toString());
                    Glide.with(SelectInterests.this)
                            .load(coastalImage[0])
                            .centerCrop()
                            .into(imgCoastal);
                    txtCoastal.setTextColor(getResources().getColor(R.color.cardview_dark_background));
                    imgCoastal.setSelected(false);

                }
            }
        });

        /*
        Load and set category image from Firebase
         */
        imgDeserts = findViewById(R.id.imgDeserts);
        final String[] desertsImage = new String[1];
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (Objects.equals(dataSnapshot.child("name").getValue(String.class), "Deserts")) {
                        desertsImage[0] = dataSnapshot.child("image").getValue(String.class);
                        Glide.with(SelectInterests.this)
                                .load(desertsImage[0])
                                .centerCrop()
                                .into(imgDeserts);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        txtDeserts = findViewById(R.id.txtDeserts);
        imgDeserts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredCategories.size() <= 14 && !imgDeserts.isSelected()) {
                    preferredCategories.add(txtDeserts.getText().toString());
                    imgDeserts.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_check_circle_outline_24));
                    imgDeserts.setAnimation(fadeIn);
                    txtDeserts.setTextColor(getResources().getColor(R.color.theme_green));
                    imgDeserts.setSelected(true);

                } else {
                    preferredCategories.remove(txtDeserts.getText().toString());
                    Glide.with(SelectInterests.this)
                            .load(desertsImage[0])
                            .centerCrop()
                            .into(imgDeserts);
                    txtDeserts.setTextColor(getResources().getColor(R.color.cardview_dark_background));
                    imgDeserts.setSelected(false);
                }
            }
        });

        /*
        Load and set category image from Firebase
         */
        imgForts = findViewById(R.id.imgForts);
        final String[] fortsImage = new String[1];
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (Objects.equals(dataSnapshot.child("name").getValue(String.class), "Forts")) {
                        fortsImage[0] = dataSnapshot.child("image").getValue(String.class);
                        Glide.with(SelectInterests.this)
                                .load(fortsImage[0])
                                .centerCrop()
                                .into(imgForts);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        txtForts = findViewById(R.id.txtForts);
        imgForts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredCategories.size() <= 14 && !imgForts.isSelected()) {
                    preferredCategories.add(txtForts.getText().toString());
                    imgForts.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_check_circle_outline_24));
                    imgForts.setAnimation(fadeIn);
                    txtForts.setTextColor(getResources().getColor(R.color.theme_green));
                    imgForts.setSelected(true);

                } else {
                    preferredCategories.remove(txtForts.getText().toString());
                    Glide.with(SelectInterests.this)
                            .load(fortsImage[0])
                            .centerCrop()
                            .into(imgForts);
                    txtForts.setTextColor(getResources().getColor(R.color.cardview_dark_background));
                    imgForts.setSelected(false);

                }
            }
        });

        /*
        Load and set category image from Firebase
         */
        imgHillStations = findViewById(R.id.imgHillStations);
        final String[] hillStationsImage = new String[1];
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (Objects.equals(dataSnapshot.child("name").getValue(String.class), "Hill Stations")) {
                        hillStationsImage[0] = dataSnapshot.child("image").getValue(String.class);
                        Glide.with(SelectInterests.this)
                                .load(hillStationsImage[0])
                                .centerCrop()
                                .into(imgHillStations);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        txtHillStations = findViewById(R.id.txtHillStations);
        imgHillStations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredCategories.size() <= 14 && !imgHillStations.isSelected()) {
                    preferredCategories.add(txtHillStations.getText().toString());
                    imgHillStations.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_check_circle_outline_24));
                    imgHillStations.setAnimation(fadeIn);
                    txtHillStations.setTextColor(getResources().getColor(R.color.theme_green));
                    imgHillStations.setSelected(true);

                } else {
                    preferredCategories.remove(txtHillStations.getText().toString());
                    Glide.with(SelectInterests.this)
                            .load(hillStationsImage[0])
                            .centerCrop()
                            .into(imgHillStations);
                    txtHillStations.setTextColor(getResources().getColor(R.color.cardview_dark_background));
                    imgHillStations.setSelected(false);

                }
            }
        });

        /*
        Load and set category image from Firebase
         */
        imgIslands = findViewById(R.id.imgIslands);
        final String[] islandsImage = new String[1];
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (Objects.equals(dataSnapshot.child("name").getValue(String.class), "Islands")) {
                        islandsImage[0] = dataSnapshot.child("image").getValue(String.class);
                        Glide.with(SelectInterests.this)
                                .load(islandsImage[0])
                                .centerCrop()
                                .into(imgIslands);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        txtIslands = findViewById(R.id.txtIslands);
        imgIslands.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredCategories.size() <= 14 && !imgIslands.isSelected()) {
                    preferredCategories.add(txtIslands.getText().toString());
                    imgIslands.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_check_circle_outline_24));
                    imgIslands.setAnimation(fadeIn);
                    txtIslands.setTextColor(getResources().getColor(R.color.theme_green));
                    imgIslands.setSelected(true);

                } else {
                    preferredCategories.remove(txtIslands.getText().toString());
                    Glide.with(SelectInterests.this)
                            .load(islandsImage[0])
                            .centerCrop()
                            .into(imgIslands);
                    txtIslands.setTextColor(getResources().getColor(R.color.cardview_dark_background));
                    imgIslands.setSelected(false);

                }
            }
        });

        /*
        Load and set category image from Firebase
         */
        imgLakes = findViewById(R.id.imgLakes);
        final String[] lakesImage = new String[1];
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (Objects.equals(dataSnapshot.child("name").getValue(String.class), "Lakes")) {
                        lakesImage[0] = dataSnapshot.child("image").getValue(String.class);
                        Glide.with(SelectInterests.this)
                                .load(lakesImage[0])
                                .centerCrop()
                                .into(imgLakes);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        txtLakes = findViewById(R.id.txtLakes);
        imgLakes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredCategories.size() <= 14 && !imgLakes.isSelected()) {
                    preferredCategories.add(txtLakes.getText().toString());
                    imgLakes.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_check_circle_outline_24));
                    imgLakes.setAnimation(fadeIn);
                    txtLakes.setTextColor(getResources().getColor(R.color.theme_green));
                    imgLakes.setSelected(true);

                } else {
                    preferredCategories.remove(txtLakes.getText().toString());
                    Glide.with(SelectInterests.this)
                            .load(lakesImage[0])
                            .centerCrop()
                            .into(imgLakes);
                    txtLakes.setTextColor(getResources().getColor(R.color.cardview_dark_background));
                    imgLakes.setSelected(false);

                }
            }
        });

        /*
        Load and set category image from Firebase
         */
        imgMines = findViewById(R.id.imgMines);
        final String[] minesImage = new String[1];
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (Objects.equals(dataSnapshot.child("name").getValue(String.class), "Mines")) {
                        minesImage[0] = dataSnapshot.child("image").getValue(String.class);
                        Glide.with(SelectInterests.this)
                                .load(minesImage[0])
                                .centerCrop()
                                .into(imgMines);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        txtMines = findViewById(R.id.txtMines);
        imgMines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredCategories.size() <= 14 && !imgMines.isSelected()) {
                    preferredCategories.add(txtMines.getText().toString());
                    imgMines.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_check_circle_outline_24));
                    imgMines.setAnimation(fadeIn);
                    txtMines.setTextColor(getResources().getColor(R.color.theme_green));
                    imgMines.setSelected(true);

                } else {
                    preferredCategories.remove(txtMines.getText().toString());
                    Glide.with(SelectInterests.this)
                            .load(minesImage[0])
                            .centerCrop()
                            .into(imgMines);
                    txtMines.setTextColor(getResources().getColor(R.color.cardview_dark_background));
                    imgMines.setSelected(false);

                }
            }
        });

        /*
        Load and set category image from Firebase
         */
        imgMonuments = findViewById(R.id.imgMonuments);
        final String[] monumentsImage = new String[1];
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (Objects.equals(dataSnapshot.child("name").getValue(String.class), "Monuments")) {
                        monumentsImage[0] = dataSnapshot.child("image").getValue(String.class);
                        Glide.with(SelectInterests.this)
                                .load(monumentsImage[0])
                                .centerCrop()
                                .into(imgMonuments);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        txtMonuments = findViewById(R.id.txtMonuments);
        imgMonuments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredCategories.size() <= 14 && !imgMonuments.isSelected()) {
                    preferredCategories.add(txtMonuments.getText().toString());
                    imgMonuments.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_check_circle_outline_24));
                    imgMonuments.setAnimation(fadeIn);
                    txtMonuments.setTextColor(getResources().getColor(R.color.theme_green));
                    imgMonuments.setSelected(true);

                } else {
                    preferredCategories.remove(txtMonuments.getText().toString());
                    Glide.with(SelectInterests.this)
                            .load(monumentsImage[0])
                            .centerCrop()
                            .into(imgMonuments);
                    txtMonuments.setTextColor(getResources().getColor(R.color.cardview_dark_background));
                    imgMonuments.setSelected(false);

                }
            }
        });

        /*
        Load and set category image from Firebase
         */
        imgMosques = findViewById(R.id.imgMosques);
        final String[] mosquesImage = new String[1];
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (Objects.equals(dataSnapshot.child("name").getValue(String.class), "Mosques")) {
                        mosquesImage[0] = dataSnapshot.child("image").getValue(String.class);
                        Glide.with(SelectInterests.this)
                                .load(mosquesImage[0])
                                .centerCrop()
                                .into(imgMosques);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        txtMosques = findViewById(R.id.txtMosques);
        imgMosques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredCategories.size() <= 14 && !imgMosques.isSelected()) {
                    preferredCategories.add(txtMosques.getText().toString());
                    imgMosques.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_check_circle_outline_24));
                    imgMosques.setAnimation(fadeIn);
                    txtMosques.setTextColor(getResources().getColor(R.color.theme_green));
                    imgMosques.setSelected(true);

                } else {
                    preferredCategories.remove(txtMosques.getText().toString());
                    Glide.with(SelectInterests.this)
                            .load(mosquesImage[0])
                            .centerCrop()
                            .into(imgMosques);
                    txtMosques.setTextColor(getResources().getColor(R.color.cardview_dark_background));
                    imgMosques.setSelected(false);

                }
            }
        });

        /*
        Load and set category image from Firebase
         */
        imgMountainous = findViewById(R.id.imgMountainous);
        final String[] mountainousImage = new String[1];
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (Objects.equals(dataSnapshot.child("name").getValue(String.class), "Mountainous")) {
                        mountainousImage[0] = dataSnapshot.child("image").getValue(String.class);
                        Glide.with(SelectInterests.this)
                                .load(mountainousImage[0])
                                .centerCrop()
                                .into(imgMountainous);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        txtMountainous = findViewById(R.id.txtMountainous);
        imgMountainous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredCategories.size() <= 14 && !imgMountainous.isSelected()) {
                    preferredCategories.add(txtMountainous.getText().toString());
                    imgMountainous.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_check_circle_outline_24));
                    imgMountainous.setAnimation(fadeIn);
                    txtMountainous.setTextColor(getResources().getColor(R.color.theme_green));
                    imgMountainous.setSelected(true);

                } else {
                    preferredCategories.remove(txtMountainous.getText().toString());
                    Glide.with(SelectInterests.this)
                            .load(mountainousImage[0])
                            .centerCrop()
                            .into(imgMountainous);
                    txtMountainous.setTextColor(getResources().getColor(R.color.cardview_dark_background));
                    imgMountainous.setSelected(false);

                }
            }
        });

        /*
        Load and set category image from Firebase
         */
        imgMuseums = findViewById(R.id.imgMuseums);
        final String[] museumsImage = new String[1];
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (Objects.equals(dataSnapshot.child("name").getValue(String.class), "Museums")) {
                        museumsImage[0] = dataSnapshot.child("image").getValue(String.class);
                        Glide.with(SelectInterests.this)
                                .load(museumsImage[0])
                                .centerCrop()
                                .into(imgMuseums);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        txtMuseums = findViewById(R.id.txtMuseums);
        imgMuseums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredCategories.size() <= 14 && !imgMuseums.isSelected()) {
                    preferredCategories.add(txtMuseums.getText().toString());
                    imgMuseums.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_check_circle_outline_24));
                    imgMuseums.setAnimation(fadeIn);
                    txtMuseums.setTextColor(getResources().getColor(R.color.theme_green));
                    imgMuseums.setSelected(true);

                } else {
                    preferredCategories.remove(txtMuseums.getText().toString());
                    Glide.with(SelectInterests.this)
                            .load(museumsImage[0])
                            .centerCrop()
                            .into(imgMuseums);
                    txtMuseums.setTextColor(getResources().getColor(R.color.cardview_dark_background));
                    imgMuseums.setSelected(false);

                }
            }
        });

        /*
        Load and set category image from Firebase
         */
        imgNationalParks = findViewById(R.id.imgNationalParks);
        final String[] nationalParksImage = new String[1];
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (Objects.equals(dataSnapshot.child("name").getValue(String.class), "National Parks")) {
                        nationalParksImage[0] = dataSnapshot.child("image").getValue(String.class);
                        Glide.with(SelectInterests.this)
                                .load(nationalParksImage[0])
                                .centerCrop()
                                .into(imgNationalParks);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        txtNationalParks = findViewById(R.id.txtNationalParks);
        imgNationalParks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredCategories.size() <= 14 && !imgNationalParks.isSelected()) {
                    preferredCategories.add(txtNationalParks.getText().toString());
                    imgNationalParks.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_check_circle_outline_24));
                    imgNationalParks.setAnimation(fadeIn);
                    txtNationalParks.setTextColor(getResources().getColor(R.color.theme_green));
                    imgNationalParks.setSelected(true);

                } else {
                    preferredCategories.remove(txtNationalParks.getText().toString());
                    Glide.with(SelectInterests.this)
                            .load(nationalParksImage[0])
                            .centerCrop()
                            .into(imgNationalParks);
                    txtNationalParks.setTextColor(getResources().getColor(R.color.cardview_dark_background));
                    imgNationalParks.setSelected(false);

                }
            }
        });

        /*
        Load and set category image from Firebase
         */
        imgTemples = findViewById(R.id.imgTemples);
        final String[] templesImage = new String[1];
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (Objects.equals(dataSnapshot.child("name").getValue(String.class), "Temples")) {
                        templesImage[0] = dataSnapshot.child("image").getValue(String.class);
                        Glide.with(SelectInterests.this)
                                .load(templesImage[0])
                                .centerCrop()
                                .into(imgTemples);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        txtTemples = findViewById(R.id.txtTemples);
        imgTemples.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredCategories.size() <= 14 && !imgTemples.isSelected()) {
                    preferredCategories.add(txtTemples.getText().toString());
                    imgTemples.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_check_circle_outline_24));
                    imgTemples.setAnimation(fadeIn);
                    txtTemples.setTextColor(getResources().getColor(R.color.theme_green));
                    imgTemples.setSelected(true);

                } else {
                    preferredCategories.remove(txtTemples.getText().toString());
                    Glide.with(SelectInterests.this)
                            .load(templesImage[0])
                            .centerCrop()
                            .into(imgTemples);
                    txtTemples.setTextColor(getResources().getColor(R.color.cardview_dark_background));
                    imgTemples.setSelected(false);

                }
            }
        });

        /*
        Load and set category image from Firebase
         */
        imgValleys = findViewById(R.id.imgValleys);
        final String[] valleysImage = new String[1];
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (Objects.equals(dataSnapshot.child("name").getValue(String.class), "Valleys")) {
                        valleysImage[0] = dataSnapshot.child("image").getValue(String.class);
                        Glide.with(SelectInterests.this)
                                .load(valleysImage[0])
                                .centerCrop()
                                .into(imgValleys);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        txtValleys = findViewById(R.id.txtValleys);
        imgValleys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredCategories.size() <= 14 && !imgValleys.isSelected()) {
                    preferredCategories.add(txtValleys.getText().toString());
                    imgValleys.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_check_circle_outline_24));
                    imgValleys.setAnimation(fadeIn);
                    txtValleys.setTextColor(getResources().getColor(R.color.theme_green));
                    imgValleys.setSelected(true);

                } else {
                    preferredCategories.remove(txtValleys.getText().toString());
                    Glide.with(SelectInterests.this)
                            .load(valleysImage[0])
                            .centerCrop()
                            .into(imgValleys);
                    txtValleys.setTextColor(getResources().getColor(R.color.cardview_dark_background));
                    imgValleys.setSelected(false);

                }
            }
        });

        /*
        Load and set category image from Firebase
         */
        imgWaterfalls = findViewById(R.id.imgWaterfalls);
        final String[] waterfallsImage = new String[1];
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (Objects.equals(dataSnapshot.child("name").getValue(String.class), "Waterfalls")) {
                        waterfallsImage[0] = dataSnapshot.child("image").getValue(String.class);
                        Glide.with(SelectInterests.this)
                                .load(waterfallsImage[0])
                                .centerCrop()
                                .into(imgWaterfalls);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        txtWaterfalls = findViewById(R.id.txtWaterfalls);
        imgWaterfalls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredCategories.size() <= 14 && !imgWaterfalls.isSelected()) {
                    preferredCategories.add(txtWaterfalls.getText().toString());
                    imgWaterfalls.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_check_circle_outline_24));
                    imgWaterfalls.setAnimation(fadeIn);
                    txtWaterfalls.setTextColor(getResources().getColor(R.color.theme_green));
                    imgWaterfalls.setSelected(true);

                } else {
                    preferredCategories.remove(txtWaterfalls.getText().toString());
                    Glide.with(SelectInterests.this)
                            .load(waterfallsImage[0])
                            .centerCrop()
                            .into(imgWaterfalls);
                    txtWaterfalls.setTextColor(getResources().getColor(R.color.cardview_dark_background));
                    imgWaterfalls.setSelected(false);

                }
            }
        });

        /*
        Button
         */
        continueBtn = findViewById(R.id.continueBtn);
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SelectInterests.this, SelectPreferences.class)
                        .putExtra("preferred_categories", preferredCategories));
                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
            }
        });
    }

    public void skip(View view) {
        startActivity(new Intent(this, Explore.class));
        finish();
    }
}