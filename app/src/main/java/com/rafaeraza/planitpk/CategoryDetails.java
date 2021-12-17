package com.rafaeraza.planitpk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CategoryDetails extends AppCompatActivity {
    RecyclerView recyclerView;
    private ArrayList<LocationHelperClass> locations;
    private ProgressBar progressBar;
    private LocationBasicAdapter locationBasicAdapter;
    LocationBasicAdapter.OnUserClickListener onUserClickListener;

    ImageView imgBackPress;
    TextView txtCategoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_details);

        imgBackPress = findViewById(R.id.backPress);
        imgBackPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CategoryDetails.this, Explore.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
                finish();
            }
        });

        onUserClickListener = position -> {
            Toast.makeText(CategoryDetails.this, "Tapped on location", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(CategoryDetails.this, LocationDetails.class)
                    .putExtra("location_name", locations.get(position).getName())
                    .putExtra("location_category", locations.get(position).getCategory())
                    .putExtra("location_description", locations.get(position).getDesc())
                    .putExtra("location_rating", locations.get(position).getRating())
                    .putExtra("img1", locations.get(position).getImages().getLink1())
                    .putExtra("img2", locations.get(position).getImages().getLink2())
                    .putExtra("img3", locations.get(position).getImages().getLink3()));
            overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
        };

        txtCategoryName = findViewById(R.id.txtCategoryName);
        txtCategoryName.setText(getIntent().getStringExtra("category_name"));

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);

        locations = new ArrayList<>();

        getLocations(getIntent().getStringExtra("category_name"));
    }

    private void getLocations(String categoryName) {
        locations.clear();
        FirebaseDatabase.getInstance().getReference("Locations").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    locations.add(dataSnapshot.getValue(LocationHelperClass.class));
                }
                locationBasicAdapter = new LocationBasicAdapter(locations, CategoryDetails.this, onUserClickListener);
                locationBasicAdapter.getFilter().filter(categoryName);
                recyclerView.setLayoutManager(new LinearLayoutManager(CategoryDetails.this));
                recyclerView.setAdapter(locationBasicAdapter);
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}