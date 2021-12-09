package com.rafaeraza.planitpk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LocationDetails extends AppCompatActivity implements RatingDialog.RatingDialogListener {

    //Variables
    ViewPager viewPager;
    LinearLayout dotsLayout;
    LocationImagesSliderAdapter sliderAdapter;
    TextView[] dots;
    Animation animation, fadeIn;
    int currentPos;

    ImageView imgBackPress, favoriteLocation;
    TextView txtLocationName, txtLocationCategory, txtLocationDescription;
    Button btnAddToTrip, btnRateLocation;

    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Locations");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_details);

        imgBackPress = findViewById(R.id.backPress);
        imgBackPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LocationDetails.this, Search.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
                finish();
            }
        });

        favoriteLocation = findViewById(R.id.favoriteLocation);
        favoriteLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!favoriteLocation.isSelected()) {
                    favoriteLocation.setImageResource(R.drawable.ic_baseline_favorite_24);
                    favoriteLocation.setSelected(true);
                } else {
                    favoriteLocation.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                    favoriteLocation.setSelected(false);
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

        fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        //Hooks
        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        txtLocationName = findViewById(R.id.txtLocationName);
        txtLocationCategory = findViewById(R.id.txtlocationCategory);
        txtLocationDescription = findViewById(R.id.txtlocationDesc);

        String name = getIntent().getStringExtra("location_name");
        txtLocationName.setText(name);
        String category = getIntent().getStringExtra("location_category");
        txtLocationCategory.setText(category);
        String description = getIntent().getStringExtra("location_description");
        txtLocationDescription.setText(description);

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
        reference.child(txtLocationName.getText().toString()).child("rating").setValue(String.valueOf(newRating));
    }
}