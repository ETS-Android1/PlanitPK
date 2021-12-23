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
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Search extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    /*
    Declarations
     */
    RecyclerView recyclerView;
    TextView currentFilter;
    CircleImageView filterBadge;
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

        /*
        Hooks
         */
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        currentFilter = findViewById(R.id.txtCurrentFilter);
        currentFilter.setVisibility(View.GONE);
        filterBadge = findViewById(R.id.filterBadge);

        locations = new ArrayList<>();

        searchView = findViewById(R.id.search_field);

        /*
        On Click Listeners
         */
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

        /*
        Function calls
         */
        getLocations();
    }

    //============================================================================================//
    /*
    Pop up menu showing list of filters
     */
    public void showFilterList(View view) {
        PopupMenu filterList = new PopupMenu(Search.this, view);
        filterList.setOnMenuItemClickListener(this);
        filterList.inflate(R.menu.filters_menu);
        filterList.show();
    }

    /*
    Overridden implemented method
     */
    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case (R.id.filter_All):
                locationBasicAdapter.getFilter().filter("a");
                currentFilter.setText("");
                currentFilter.setVisibility(View.GONE);
                filterBadge.setVisibility(View.GONE);
                break;
            case(R.id.filter_Balochistan):
                locationBasicAdapter.getFilter().filter("Balochistan");
                currentFilter.setText("(You are currently viewing Balochistan)");
                currentFilter.setVisibility(View.VISIBLE);
                filterBadge.setVisibility(View.VISIBLE);
                break;
            case (R.id.filter_Fata):
                locationBasicAdapter.getFilter().filter("Fata");
                currentFilter.setText("(You are currently viewing FATA)");
                currentFilter.setVisibility(View.VISIBLE);
                filterBadge.setVisibility(View.VISIBLE);
                break;
            case(R.id.filter_Gilgit):
                locationBasicAdapter.getFilter().filter("Gilgit");
                currentFilter.setText("(You are currently viewing Gilgit-Baltistan)");
                currentFilter.setVisibility(View.VISIBLE);
                filterBadge.setVisibility(View.VISIBLE);
                break;
            case(R.id.filter_Islamabad):
                locationBasicAdapter.getFilter().filter("Islamabad");
                currentFilter.setText("(You are currently viewing Islamabad)");
                currentFilter.setVisibility(View.VISIBLE);
                filterBadge.setVisibility(View.VISIBLE);
                break;
            case(R.id.filter_Kashmir):
                locationBasicAdapter.getFilter().filter("Azad Kashmir");
                currentFilter.setText("(You are currently viewing Azad Kashmir)");
                currentFilter.setVisibility(View.VISIBLE);
                filterBadge.setVisibility(View.VISIBLE);
                break;
            case(R.id.filter_KPK):
                locationBasicAdapter.getFilter().filter("Khyber Pakhtun");
                currentFilter.setText("(You are currently viewing KPK)");
                currentFilter.setVisibility(View.VISIBLE);
                filterBadge.setVisibility(View.VISIBLE);
                break;
            case(R.id.filter_Punjab):
                locationBasicAdapter.getFilter().filter("Punjab");
                currentFilter.setText("(You are currently viewing Punjab)");
                currentFilter.setVisibility(View.VISIBLE);
                filterBadge.setVisibility(View.VISIBLE);
                break;
            case(R.id.filter_Sindh):
                locationBasicAdapter.getFilter().filter("Sindh");
                currentFilter.setText("(You are currently viewing Sindh)");
                currentFilter.setVisibility(View.VISIBLE);
                filterBadge.setVisibility(View.VISIBLE);
                break;
            default:
                filterBadge.setVisibility(View.GONE);
                return true;
        }
        return false;
    }

    /*
    Function to retrieve locations from Firebase
     */
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