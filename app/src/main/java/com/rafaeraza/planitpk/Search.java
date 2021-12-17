package com.rafaeraza.planitpk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Search extends AppCompatActivity {

    RecyclerView recyclerView;
    private ArrayList<LocationHelperClass> locations;
    private ProgressBar progressBar;
    private LocationBasicAdapter locationBasicAdapter;
    LocationBasicAdapter.OnUserClickListener onUserClickListener;
    SearchView searchView;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);

        locations = new ArrayList<>();

        searchView = findViewById(R.id.search_field);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                locationBasicAdapter.getFilter().filter(newText);
                return false;
            }
        });

        onUserClickListener = position -> {
            Toast.makeText(Search.this, "Tapped on location", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Search.this, LocationDetails.class)
                    .putExtra("location_name", locations.get(position).getName())
                    .putExtra("location_category", locations.get(position).getCategory())
                    .putExtra("location_description", locations.get(position).getDesc())
                    .putExtra("location_rating", locations.get(position).getRating())
                    .putExtra("img1", locations.get(position).getImages().getLink1())
                    .putExtra("img2", locations.get(position).getImages().getLink2())
                    .putExtra("img3", locations.get(position).getImages().getLink3()));
            overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
        };

        getLocations();

        // Bottom Nav Bar
        bottomNavigationView = findViewById(R.id.bottom_Navigation);
        bottomNavigationView.setSelectedItemId(R.id.search);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.explore:
                        startActivity(new Intent(getApplicationContext(), Explore.class));
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        return true;
                    case R.id.plan:
                        startActivity(new Intent(getApplicationContext(), PlanTour.class));
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        return true;
                    case R.id.account:
                        startActivity(new Intent(getApplicationContext(), Account.class));
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        return true;
                }
                return false;
            }
        });
    }

    private void getLocations() {
        locations.clear();
        FirebaseDatabase.getInstance().getReference("Locations").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    locations.add(dataSnapshot.getValue(LocationHelperClass.class));
                }
                locationBasicAdapter = new LocationBasicAdapter(locations, Search.this, onUserClickListener);
                recyclerView.setLayoutManager(new LinearLayoutManager(Search.this));
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