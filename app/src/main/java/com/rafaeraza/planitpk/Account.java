package com.rafaeraza.planitpk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class Account extends AppCompatActivity {

    MaterialToolbar toolbar;

    TextView userFullName, userEmail;
    CircleImageView profileImg;
    Button logoutBtn;

    BottomNavigationView bottomNavigationView;

    FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        toolbar = findViewById(R.id.topAppBar_profile);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.itemEditProfile) {
                    startActivity(new Intent(Account.this, EditProfile.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
                }

                return true;
            }
        });

        userFullName = findViewById(R.id.txtFullName);
        userEmail = findViewById(R.id.txtEmail);

        profileImg = findViewById(R.id.imgProfilePicture);

        if (Objects.equals(Objects.requireNonNull(mAuth.getCurrentUser()).getEmail(), "guest@guest.com")) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(Account.this, SignUp.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
            finish();
        }

        showAllUserData();

        logoutBtn = findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOutUser();
            }
        });

        // Bottom Nav Bar
        bottomNavigationView = findViewById(R.id.bottom_Navigation);
        bottomNavigationView.setSelectedItemId(R.id.account);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.explore:
                        startActivity(new Intent(getApplicationContext(), Explore.class));
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        return true;
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(), Search.class));
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        return true;
                    case R.id.plan:
                        startActivity(new Intent(getApplicationContext(), PlanTour.class));
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        return true;
                }
                return false;
            }
        });
    }

    private void signOutUser() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(Account.this, Login.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
        finish();
    }


    private void showAllUserData() {

        mAuth = FirebaseAuth.getInstance();
        String userID = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("Users").child(userID);
        reference.keepSynced(true);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String fullName = Objects.requireNonNull(dataSnapshot.child("name").getValue()).toString();
                    String email = Objects.requireNonNull(dataSnapshot.child("email").getValue()).toString();
                    String profilePicture = Objects.requireNonNull(dataSnapshot.child("profilePicture").getValue()).toString();


                    if (!fullName.isEmpty()) {
                        userFullName.setText(fullName);
                    } else {
                        String defaultLabel = "User ID: " + userID;
                        userFullName.setText(defaultLabel);
                    }
                    userEmail.setText(email);

                    if (!profilePicture.isEmpty()) {
                        Glide.with(Account.this)
                                .load(profilePicture)
                                .centerCrop()
                                .into(profileImg);
                    } else {
                        Glide.with(Account.this)
                                .load(R.drawable.ic_baseline_profile_circle_24)
                                .centerCrop()
                                .into(profileImg);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Account.this, "Failed to retrieve data", Toast.LENGTH_SHORT).show();
            }
        });

    }

    ;

    @SuppressLint("DefaultLocale")
    public static String formatNumber(long count) {
        if (count < 1000) return "" + count;
        int exp = (int) (Math.log(count) / Math.log(1000));
        return String.format("%.1f %c", count / Math.pow(1000, exp), "kMBTPE".charAt(exp - 1));
    }
}