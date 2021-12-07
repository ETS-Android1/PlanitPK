package com.rafaeraza.planitpk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Welcome extends AppCompatActivity {

    Button getStarted;
    TextView txtWelcomeMsg, skipMsg;
    Animation topAnim;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null) {
            Intent intent = new Intent(Welcome.this, Explore.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
            finish();
        }

        //Animation
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);

        txtWelcomeMsg = findViewById(R.id.txtWelcomeMsg);
        txtWelcomeMsg.setAnimation(topAnim);

        skipMsg = findViewById(R.id.skip_btn);
        String htmlString="<u>Skip</u>";
        skipMsg.setText(Html.fromHtml(htmlString));

        getStarted = findViewById(R.id.getStartedBtn);
        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Welcome.this, OnBoarding.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
                finish();
            }
        });
    }

    public void skip(View view) {
        startActivity(new Intent(this, Login.class));
        finish();
    }
}