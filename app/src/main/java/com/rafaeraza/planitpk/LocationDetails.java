package com.rafaeraza.planitpk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LocationDetails extends AppCompatActivity implements RatingDialog.RatingDialogListener {

    //Variables
    ViewPager viewPager;
    LinearLayout dotsLayout;
    LocationImagesSliderAdapter sliderAdapter;
    TextView[] dots;
    Animation fadeIn;
    int currentPos;

    ImageView imgBackPress, favoriteLocation;
    TextView txtLocationName, txtLocationCategory, txtLocationDescription, txtLocationRating
            ,txtUserRating;
    Button btnAddToTrip, btnRateLocation;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Locations");
    DatabaseReference currUserRef = FirebaseDatabase.getInstance().getReference()
            .child("Users/" + user.getUid());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_details);

        String currLocation = getIntent().getStringExtra("location_name");
        favoriteLocation = findViewById(R.id.favoriteLocation);

        currUserRef.child("preferences").child("favorites").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    if (Objects.equals(dataSnapshot.getValue(), currLocation)) {
                        favoriteLocation.setImageResource(R.drawable.ic_baseline_favorite_24);
                        favoriteLocation.setSelected(true);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        imgBackPress = findViewById(R.id.backPress);
        imgBackPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        final LocationHelperClass[] location = new LocationHelperClass[1];
        reference
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        location[0] = snapshot.child(currLocation)
                                .getValue(LocationHelperClass.class);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        favoriteLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!favoriteLocation.isSelected()) {
                    favoriteLocation.setImageResource(R.drawable.ic_baseline_favorite_24);
                    favoriteLocation.setSelected(true);
                    currUserRef.child("preferences").child("favorites")
                            .child(currLocation)
                            .setValue(currLocation)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "Added to favorites", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Failed to set preferences!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                } else {
                    favoriteLocation.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                    favoriteLocation.setSelected(false);
                    currUserRef.child("preferences").child("favorites")
                            .child(currLocation)
                            .setValue(null).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Removed from favorites", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Failed to set preferences!", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });

        btnAddToTrip = findViewById(R.id.btnAddToTrip);
        btnAddToTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LocationDetails.this, "Tapped on button", Toast.LENGTH_SHORT).show();
            }
        });

        btnRateLocation = findViewById(R.id.btnRateLocation);
        btnRateLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callRatingDialog();
            }
        });

        fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in_slow);

        //Hooks
        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        txtLocationName = findViewById(R.id.txtLocationName);
        txtLocationCategory = findViewById(R.id.txtlocationCategory);
        txtLocationDescription = findViewById(R.id.txtlocationDesc);
        txtLocationRating = findViewById(R.id.txtLocationRating);
        txtUserRating = findViewById(R.id.txtUserRating);

        String name = getIntent().getStringExtra("location_name");
        txtLocationName.setText(name);
        String category = getIntent().getStringExtra("location_category");
        txtLocationCategory.setText(category);
        String description = getIntent().getStringExtra("location_description");
        txtLocationDescription.setText(description);
        String rating = getIntent().getStringExtra("location_rating");
        if (!rating.isEmpty()) {
            txtLocationRating.setText(rating);
        } else {
            txtLocationRating.setText("-");
        }

        currUserRef.child("preferences").child("rated_locations")
                .child(currLocation)
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (Objects.equals(snapshot.child("locationName").getValue(String.class), currLocation)) {
                        String userDBRating = (Objects.requireNonNull(snapshot.child("userRating").getValue(Integer.class))).toString();
                        txtUserRating.setText(userDBRating);
                    } else {
                        txtUserRating.setText("-");
                    }
                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LocationDetails.this, "Database error", Toast.LENGTH_SHORT).show();
                txtUserRating.setText("-");
            }
        });

        String img1 = getIntent().getStringExtra("img1");
        String img2 = getIntent().getStringExtra("img2");
        String img3 = getIntent().getStringExtra("img3");

        //Call adapter
        sliderAdapter = new LocationImagesSliderAdapter(this, img1, img2, img3);
        viewPager.setAdapter(sliderAdapter);

        //Dots
        addDots(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                addDots(position);
                currentPos = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void callRatingDialog() {
        RatingDialog ratingDialog = new RatingDialog();
        ratingDialog.show(getSupportFragmentManager(), "Rating Dialog");
    }

    public String sendLocationName() {
        return getIntent().getStringExtra("location_name");
    }

    private void addDots(int position) {

        dots = new TextView[3];
        dotsLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("•"));
            dots[i].setTextSize(35);

            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.white));
        }

    }

    @Override
    public void setRating(int newRating) {
        String currLocation = txtLocationName.getText().toString();
        RatingHelperClass userRating = new RatingHelperClass(currLocation, newRating);

        currUserRef.child("preferences").child("rated_locations")
                .child(currLocation)
                .setValue(userRating).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    txtUserRating.setText(String.valueOf(newRating));
                    Toast.makeText(LocationDetails.this, "Your rating added!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LocationDetails.this, "Failed to add rating!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        reference.child(currLocation)
                .child("rating").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (Objects.requireNonNull(snapshot.getValue()).toString().isEmpty()) {
                    reference.child(currLocation).child("rating").setValue(String.valueOf(newRating));
                    Toast.makeText(LocationDetails.this, "Rating added!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LocationDetails.this, "Already rated!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }
}