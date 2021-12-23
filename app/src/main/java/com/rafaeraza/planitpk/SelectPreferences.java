package com.rafaeraza.planitpk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SelectPreferences extends AppCompatActivity {

    /*
    Declarations
     */
    ImageView imgBackPress;
    CardView punjabCV, sindhCV, balochCV, islamabadCV, kpkCV, gilgitCV, akCV, fataCV, hikingCV,
            cyclingCV, trekkingCV, skiingCV, rockClimbingCV, paraSailingCV, campingCV, boatingCV, sightseeingCV;
    TextView txtPunjab, txtSindh, txtBaloch, txtIslamabad, txtKPK, txtGilgit, txtAK, txtFATA,
            txtHiking, txtCycling, txtTrekking, txtSkiing, txtRockClimbing, txtParaSailing,
            txtCamping, txtBoating, txtSightSeeing;
    Button continueBtn;

    private ArrayList<String> preferredCategories, preferredDistricts, preferredActivities;
    private String favoriteLocations = "";

    private PreferenceHelperClass preferencesList;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users/" + user.getUid());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_preferences);

        imgBackPress = findViewById(R.id.backPress);
        imgBackPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SelectPreferences.this, SelectInterests.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
                finish();
            }
        });

        preferredCategories = new ArrayList<>();
        preferredDistricts = new ArrayList<>();
        preferredActivities = new ArrayList<>();

        punjabCV = findViewById(R.id.cvPunjab);
        txtPunjab = findViewById(R.id.txtPunjab);
        punjabCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredDistricts.size() <= 7 && !punjabCV.isSelected()) {
                    preferredDistricts.add(txtPunjab.getText().toString());
                    punjabCV.setCardBackgroundColor(getResources().getColor(R.color.theme_green));
                    punjabCV.setSelected(true);

                } else {
                    preferredDistricts.remove(txtPunjab.getText().toString());
                    punjabCV.setCardBackgroundColor(getResources().getColor(R.color.white));
                    punjabCV.setSelected(false);

                }
            }
        });

        sindhCV = findViewById(R.id.cvSindh);
        txtSindh = findViewById(R.id.txtSindh);
        sindhCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredDistricts.size() <= 7 && !sindhCV.isSelected()) {
                    preferredDistricts.add(txtSindh.getText().toString());
                    sindhCV.setCardBackgroundColor(getResources().getColor(R.color.theme_green));
                    sindhCV.setSelected(true);

                } else {
                    preferredDistricts.remove(txtSindh.getText().toString());
                    sindhCV.setCardBackgroundColor(getResources().getColor(R.color.white));
                    sindhCV.setSelected(false);

                }
            }
        });

        balochCV = findViewById(R.id.cvBalochistan);
        txtBaloch = findViewById(R.id.txtBalochistan);
        balochCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredDistricts.size() <= 7 && !balochCV.isSelected()) {
                    preferredDistricts.add(txtBaloch.getText().toString());
                    balochCV.setCardBackgroundColor(getResources().getColor(R.color.theme_green));
                    balochCV.setSelected(true);

                } else {
                    preferredDistricts.remove(txtBaloch.getText().toString());
                    balochCV.setCardBackgroundColor(getResources().getColor(R.color.white));
                    balochCV.setSelected(false);

                }
            }
        });

        islamabadCV = findViewById(R.id.cvIslamabad);
        txtIslamabad = findViewById(R.id.txtIslamabad);
        islamabadCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredDistricts.size() <= 7 && !islamabadCV.isSelected()) {
                    preferredDistricts.add(txtIslamabad.getText().toString());
                    islamabadCV.setCardBackgroundColor(getResources().getColor(R.color.theme_green));
                    islamabadCV.setSelected(true);

                } else {
                    preferredDistricts.remove(txtIslamabad.getText().toString());
                    islamabadCV.setCardBackgroundColor(getResources().getColor(R.color.white));
                    islamabadCV.setSelected(false);

                }
            }
        });

        kpkCV = findViewById(R.id.cvKPK);
        txtKPK = findViewById(R.id.txtKPK);
        kpkCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredDistricts.size() <= 7 && !kpkCV.isSelected()) {
                    preferredDistricts.add(txtKPK.getText().toString());
                    kpkCV.setCardBackgroundColor(getResources().getColor(R.color.theme_green));
                    kpkCV.setSelected(true);

                } else {
                    preferredDistricts.remove(txtKPK.getText().toString());
                    kpkCV.setCardBackgroundColor(getResources().getColor(R.color.white));
                    kpkCV.setSelected(false);

                }
            }
        });

        gilgitCV = findViewById(R.id.cvGilgit);
        txtGilgit = findViewById(R.id.txtGilgit);
        gilgitCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredDistricts.size() <= 7 && !gilgitCV.isSelected()) {
                    preferredDistricts.add(txtGilgit.getText().toString());
                    gilgitCV.setCardBackgroundColor(getResources().getColor(R.color.theme_green));
                    gilgitCV.setSelected(true);

                } else {
                    preferredDistricts.remove(txtGilgit.getText().toString());
                    gilgitCV.setCardBackgroundColor(getResources().getColor(R.color.white));
                    gilgitCV.setSelected(false);

                }
            }
        });

        akCV = findViewById(R.id.cvAzadKashmir);
        txtAK = findViewById(R.id.txtAzadKashmir);
        akCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredDistricts.size() <= 7 && !akCV.isSelected()) {
                    preferredDistricts.add(txtAK.getText().toString());
                    akCV.setCardBackgroundColor(getResources().getColor(R.color.theme_green));
                    akCV.setSelected(true);

                } else {
                    preferredDistricts.remove(txtAK.getText().toString());
                    akCV.setCardBackgroundColor(getResources().getColor(R.color.white));
                    akCV.setSelected(false);

                }
            }
        });

        fataCV = findViewById(R.id.cvFATA);
        txtFATA = findViewById(R.id.txtFATA);
        fataCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredDistricts.size() <= 7 && !fataCV.isSelected()) {
                    preferredDistricts.add(txtFATA.getText().toString());
                    fataCV.setCardBackgroundColor(getResources().getColor(R.color.theme_green));
                    fataCV.setSelected(true);

                } else {
                    preferredDistricts.remove(txtFATA.getText().toString());
                    fataCV.setCardBackgroundColor(getResources().getColor(R.color.white));
                    fataCV.setSelected(false);

                }
            }
        });

        hikingCV = findViewById(R.id.cvHiking);
        txtHiking = findViewById(R.id.txtHiking);
        hikingCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredActivities.size() <= 8 && !hikingCV.isSelected()) {
                    preferredActivities.add(txtHiking.getText().toString());
                    hikingCV.setCardBackgroundColor(getResources().getColor(R.color.theme_green));
                    hikingCV.setSelected(true);

                } else {
                    preferredActivities.remove(txtHiking.getText().toString());
                    hikingCV.setCardBackgroundColor(getResources().getColor(R.color.white));
                    hikingCV.setSelected(false);

                }
            }
        });

        cyclingCV = findViewById(R.id.cvCycling);
        txtCycling = findViewById(R.id.txtCycling);
        cyclingCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredActivities.size() <= 8 && !cyclingCV.isSelected()) {
                    preferredActivities.add(txtCycling.getText().toString());
                    cyclingCV.setCardBackgroundColor(getResources().getColor(R.color.theme_green));
                    cyclingCV.setSelected(true);

                } else {
                    preferredActivities.remove(txtCycling.getText().toString());
                    cyclingCV.setCardBackgroundColor(getResources().getColor(R.color.white));
                    cyclingCV.setSelected(false);

                }
            }
        });

        trekkingCV = findViewById(R.id.cvTrekking);
        txtTrekking = findViewById(R.id.txtTrekking);
        trekkingCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredActivities.size() <= 8 && !trekkingCV.isSelected()) {
                    preferredActivities.add(txtTrekking.getText().toString());
                    trekkingCV.setCardBackgroundColor(getResources().getColor(R.color.theme_green));
                    trekkingCV.setSelected(true);

                } else {
                    preferredActivities.remove(txtTrekking.getText().toString());
                    trekkingCV.setCardBackgroundColor(getResources().getColor(R.color.white));
                    trekkingCV.setSelected(false);

                }
            }
        });

        skiingCV = findViewById(R.id.cvSkiing);
        txtSkiing = findViewById(R.id.txtSkiing);
        skiingCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredActivities.size() <= 8 && !skiingCV.isSelected()) {
                    preferredActivities.add(txtSkiing.getText().toString());
                    skiingCV.setCardBackgroundColor(getResources().getColor(R.color.theme_green));
                    skiingCV.setSelected(true);

                } else {
                    preferredActivities.remove(txtSkiing.getText().toString());
                    skiingCV.setCardBackgroundColor(getResources().getColor(R.color.white));
                    skiingCV.setSelected(false);

                }
            }
        });

        rockClimbingCV = findViewById(R.id.cvRockClimbing);
        txtRockClimbing = findViewById(R.id.txtRockClimbing);
        rockClimbingCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredActivities.size() <= 8 && !rockClimbingCV.isSelected()) {
                    preferredActivities.add(txtRockClimbing.getText().toString());
                    rockClimbingCV.setCardBackgroundColor(getResources().getColor(R.color.theme_green));
                    rockClimbingCV.setSelected(true);

                } else {
                    preferredActivities.remove(txtRockClimbing.getText().toString());
                    rockClimbingCV.setCardBackgroundColor(getResources().getColor(R.color.white));
                    rockClimbingCV.setSelected(false);

                }
            }
        });

        paraSailingCV = findViewById(R.id.cvParaSailing);
        txtParaSailing = findViewById(R.id.txtParaSailing);
        paraSailingCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredActivities.size() <= 8 && !paraSailingCV.isSelected()) {
                    preferredActivities.add(txtParaSailing.getText().toString());
                    paraSailingCV.setCardBackgroundColor(getResources().getColor(R.color.theme_green));
                    paraSailingCV.setSelected(true);

                } else {
                    preferredActivities.remove(txtParaSailing.getText().toString());
                    paraSailingCV.setCardBackgroundColor(getResources().getColor(R.color.white));
                    paraSailingCV.setSelected(false);

                }
            }
        });

        campingCV = findViewById(R.id.cvCamping);
        txtCamping = findViewById(R.id.txtCamping);
        campingCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredActivities.size() <= 8 && !campingCV.isSelected()) {
                    preferredActivities.add(txtCamping.getText().toString());
                    campingCV.setCardBackgroundColor(getResources().getColor(R.color.theme_green));
                    campingCV.setSelected(true);

                } else {
                    preferredActivities.remove(txtCamping.getText().toString());
                    campingCV.setCardBackgroundColor(getResources().getColor(R.color.white));
                    campingCV.setSelected(false);

                }
            }
        });

        boatingCV = findViewById(R.id.cvBoating);
        txtBoating = findViewById(R.id.txtBoating);
        boatingCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredActivities.size() <= 8 && !boatingCV.isSelected()) {
                    preferredActivities.add(txtBoating.getText().toString());
                    boatingCV.setCardBackgroundColor(getResources().getColor(R.color.theme_green));
                    boatingCV.setSelected(true);

                } else {
                    preferredActivities.remove(txtBoating.getText().toString());
                    boatingCV.setCardBackgroundColor(getResources().getColor(R.color.white));
                    boatingCV.setSelected(false);

                }
            }
        });

        sightseeingCV = findViewById(R.id.cvSightSeeing);
        txtSightSeeing = findViewById(R.id.txtSightSeeing);
        sightseeingCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferredActivities.size() <= 8 && !sightseeingCV.isSelected()) {
                    preferredActivities.add(txtSightSeeing.getText().toString());
                    sightseeingCV.setCardBackgroundColor(getResources().getColor(R.color.theme_green));
                    sightseeingCV.setSelected(true);

                } else {
                    preferredActivities.remove(txtSightSeeing.getText().toString());
                    sightseeingCV.setCardBackgroundColor(getResources().getColor(R.color.white));
                    sightseeingCV.setSelected(false);

                }
            }
        });

        preferredCategories = getIntent().getStringArrayListExtra("preferred_categories");
        preferencesList = new PreferenceHelperClass(preferredCategories, preferredDistricts,
                preferredActivities, favoriteLocations);

        continueBtn = findViewById(R.id.confirmBtn);
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUserPreferences(view);
            }
        });
    }

    //=============================================================================================//
    /*
    Function to set user preferences in Firebase
     */
    private void setUserPreferences(View view) {
        reference.child("preferences").setValue(preferencesList).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SelectPreferences.this, "Preferences set successfully", Toast.LENGTH_LONG).show();
                    ProgressDialog progressDialog = new ProgressDialog(SelectPreferences.this);
                    progressDialog.setTitle("Customizing your experience...");
                    progressDialog.show();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(SelectPreferences.this, Explore.class));
                            finish();
                        }
                    }, 3000);
                } else {
                    Toast.makeText(SelectPreferences.this, "Failed to set preferences!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /*
    Utility function to provide skip functionality
     */
    public void skip(View view) {
        startActivity(new Intent(this, Explore.class));
        finish();
    }
}