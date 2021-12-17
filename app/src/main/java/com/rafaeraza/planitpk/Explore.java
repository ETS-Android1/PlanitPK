package com.rafaeraza.planitpk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import de.hdodenhof.circleimageview.CircleImageView;

public class Explore extends AppCompatActivity {

    CircleImageView profileImg;
    ImageView notificationsImg;
    TextView fullNameLabel;
    RecyclerView recyclerViewCategories, recyclerViewRecommendations, recyclerViewTopTrips;
    BottomNavigationView bottomNavigationView;

    private ArrayList<CategoryHelperClass> categories;
    private ArrayList<LocationHelperClass> recommendedLocations, topTrips;
    private ProgressBar progressBarCategories, progressBarRecommendations, progressBarTopTrips;
    private CategoryAdapter categoryAdapter;
    private LocationMiniAdapter locationsRecommendationsAdapter;
    private LocationMiniRatingAdapter topTripsAdapter;
    CategoryAdapter.OnUserClickListener onCategoryClickListener;
    LocationMiniAdapter.OnUserClickListener onLocationClickListener;
    LocationMiniRatingAdapter.OnUserClickListener onRatedLocationClickListener;

    private FirebaseAuth mAuth;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users/" + user.getUid());
    DatabaseReference locRef = FirebaseDatabase.getInstance().getReference().child("Locations");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        profileImg = findViewById(R.id.profile_image);
        notificationsImg = findViewById(R.id.notifications_img);
        fullNameLabel = findViewById(R.id.txtUserName);

        showAllUserData();

        progressBarCategories= findViewById(R.id.progressBarCategories);
        progressBarRecommendations= findViewById(R.id.progressBarRecommendations);
        progressBarTopTrips= findViewById(R.id.progressBarTopTrips);
        categories = new ArrayList<>();
        recommendedLocations = new ArrayList<>();
        topTrips = new ArrayList<>();
        recyclerViewCategories = findViewById(R.id.recyclerViewCategories);
        recyclerViewRecommendations = findViewById(R.id.recyclerViewRecommendations);
        recyclerViewTopTrips = findViewById(R.id.recyclerViewTopTrips);

        onCategoryClickListener = position -> {
            Toast.makeText(Explore.this, "Tapped on category", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Explore.this, CategoryDetails.class).putExtra("category_name", categories.get(position).getName()));
            overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
        };

        onLocationClickListener = position -> {
            Toast.makeText(Explore.this, "Tapped on location", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Explore.this, LocationDetails.class)
                    .putExtra("location_name", recommendedLocations.get(position).getName())
                    .putExtra("location_category", recommendedLocations.get(position).getCategory())
                    .putExtra("location_description", recommendedLocations.get(position).getDesc())
                    .putExtra("location_rating", recommendedLocations.get(position).getRating())
                    .putExtra("img1", recommendedLocations.get(position).getImages().getLink1())
                    .putExtra("img2", recommendedLocations.get(position).getImages().getLink2())
                    .putExtra("img3", recommendedLocations.get(position).getImages().getLink3()));
            overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
        };

        onRatedLocationClickListener = position -> {
            Toast.makeText(Explore.this, "Tapped on location", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Explore.this, LocationDetails.class)
                    .putExtra("location_name", topTrips.get(position).getName())
                    .putExtra("location_category", topTrips.get(position).getCategory())
                    .putExtra("location_description", topTrips.get(position).getDesc())
                    .putExtra("location_rating", topTrips.get(position).getRating())
                    .putExtra("img1", topTrips.get(position).getImages().getLink1())
                    .putExtra("img2", topTrips.get(position).getImages().getLink2())
                    .putExtra("img3", topTrips.get(position).getImages().getLink3()));
            overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
        };

        getCategories();
        getRecommendedLocations();
        getTopTrips();

        // Bottom Nav Bar
        bottomNavigationView = findViewById(R.id.bottom_Navigation);
        bottomNavigationView.setSelectedItemId(R.id.explore);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(), Search.class));
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

    private void showAllUserData() {

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String fullName = dataSnapshot.child("name").getValue().toString();
                    String profilePicture = dataSnapshot.child("profilePicture").getValue().toString();

                    fullNameLabel.setText(fullName);

                    if (!profilePicture.isEmpty()) {
                        Glide.with(Explore.this)
                                .load(profilePicture)
                                .centerCrop()
                                .into(profileImg);
                    } else {
                        Glide.with(Explore.this)
                                .load(R.drawable.ic_baseline_account_circle_24)
                                .centerCrop()
                                .into(profileImg);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Explore.this, "Failed to retrieve data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Retrieve locations from Firebase and setup recyclerView
    private void getCategories() {
        FirebaseDatabase.getInstance().getReference().child("Categories").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    categories.add(dataSnapshot1.getValue(CategoryHelperClass.class));
                }

                categoryAdapter = new CategoryAdapter(categories, Explore.this, onCategoryClickListener);
                recyclerViewCategories.setLayoutManager(new LinearLayoutManager(Explore.this, LinearLayoutManager.HORIZONTAL, false));
                recyclerViewCategories.setAdapter(categoryAdapter);
                progressBarCategories.setVisibility(View.GONE);
                recyclerViewCategories.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    // Retrieve locations from Firebase and setup recyclerView
    private void getRecommendedLocations() {
        List<String> recommended = new ArrayList<>();
        reference.child("preferences").child("recommendations").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    recommended.add(dataSnapshot.getValue(String.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        locRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    for (String locationName: recommended) {
                        if (Objects.equals(dataSnapshot1.child("name").getValue(String.class), locationName))
                            recommendedLocations.add(dataSnapshot1.getValue(LocationHelperClass.class));
                    }
                }

                locationsRecommendationsAdapter = new LocationMiniAdapter(recommendedLocations, Explore.this, onLocationClickListener);
                recyclerViewRecommendations.setLayoutManager(new LinearLayoutManager(Explore.this, LinearLayoutManager.HORIZONTAL, false));
                recyclerViewRecommendations.setAdapter(locationsRecommendationsAdapter);
                progressBarRecommendations.setVisibility(View.GONE);
                recyclerViewRecommendations.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    // Retrieve locations from Firebase and setup recyclerView
    private void getTopTrips() {
        locRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String rating = dataSnapshot1.child("rating").getValue(String.class);
                    if (rating != null && rating.equals("5")) {
                        topTrips.add(dataSnapshot1.getValue(LocationHelperClass.class));
                    }
                }

                topTripsAdapter = new LocationMiniRatingAdapter(topTrips, Explore.this, onRatedLocationClickListener);
                recyclerViewTopTrips.setLayoutManager(new LinearLayoutManager(Explore.this, LinearLayoutManager.HORIZONTAL, false));
                recyclerViewTopTrips.setAdapter(topTripsAdapter);
                progressBarTopTrips.setVisibility(View.GONE);
                recyclerViewTopTrips.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}