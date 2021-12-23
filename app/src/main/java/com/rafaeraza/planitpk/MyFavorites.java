package com.rafaeraza.planitpk;

import static com.rafaeraza.planitpk.R.color.green_dark;
import static com.rafaeraza.planitpk.R.color.green_light;
import static com.rafaeraza.planitpk.R.color.off_white;
import static com.rafaeraza.planitpk.R.color.white;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class MyFavorites extends AppCompatActivity {
    /*
    Declarations
     */
    MaterialToolbar toolbar;

    RecyclerView recyclerView;
    private ArrayList<String> favorites;
    private ArrayList<LocationHelperClass> locations;
    private ProgressBar progressBar;
    private LocationBasicAdapter locationBasicAdapter;
    LocationBasicAdapter.OnUserClickListener onUserClickListener;

    FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favorites);

        /*
        Hooks
         */

        toolbar = findViewById(R.id.topAppBar_myFavorites);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);

        locations = new ArrayList<>();
        favorites = new ArrayList<>();

        /*
        On Click Listeners
         */
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_search) {
                    SearchView searchView = (SearchView) item.getActionView();
                    searchView.setQueryHint("Search...");
                    searchView.setBackgroundColor(getColor(off_white));
                    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String query) {
                            locationBasicAdapter.getFilter().filter(query);
                            return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {
                            locationBasicAdapter.getFilter().filter(newText);
                            return false;
                        }
                    });
                }
                return true;
            }
        });

        onUserClickListener = position -> {
            Toast.makeText(MyFavorites.this, "Tapped on location", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MyFavorites.this, LocationDetails.class)
                    .putExtra("location_name", locations.get(position).getName())
                    .putExtra("location_category", locations.get(position).getCategory())
                    .putExtra("location_description", locations.get(position).getDesc())
                    .putExtra("location_rating", locations.get(position).getRating())
                    .putExtra("img1", locations.get(position).getImages().getLink1())
                    .putExtra("img2", locations.get(position).getImages().getLink2())
                    .putExtra("img3", locations.get(position).getImages().getLink3()));
            overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
        };

        /*
        Function calls
         */
        getFavoriteLocations();
    }

    //=============================================================================================//
    /*
    Function to retrieve user's favorite locations from Firebase
     */
    private void getFavoriteLocations() {
        mAuth = FirebaseAuth.getInstance();
        String userID = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("Users").child(userID).child("preferences")
                .child("favorites");
        reference.keepSynced(true);

        locations.clear();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    favorites.clear();
                    for (DataSnapshot favorite : snapshot.getChildren()) {
                        favorites.add(favorite.getValue(String.class));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        FirebaseDatabase.getInstance().getReference("Locations").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    for (String favLocation : favorites) {
                        if (Objects.requireNonNull(dataSnapshot.getValue(LocationHelperClass.class)).getName().equals(favLocation)) {
                            locations.add(dataSnapshot.getValue(LocationHelperClass.class));
                        }
                    }
                }
                locationBasicAdapter = new LocationBasicAdapter(locations, MyFavorites.this, onUserClickListener);
                recyclerView.setLayoutManager(new LinearLayoutManager(MyFavorites.this));
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