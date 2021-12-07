package com.rafaeraza.planitpk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    //Variables
    Animation fadeIn;
    ImageView imgLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Animation
        fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        imgLogo = findViewById(R.id.imgLogo);
        imgLogo.setAnimation(fadeIn);

        int SPLASH_SCREEN_DELAY = 3000;

        //Delay Handler
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Welcome.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
                finish();
            }
        }, SPLASH_SCREEN_DELAY);
    }
}