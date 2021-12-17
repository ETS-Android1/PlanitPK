package com.rafaeraza.planitpk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    //Variables
    ImageView imgBackPress;
    TextInputLayout inputEmail;
    Button resetPwdBtn;
    TextView signUpHint;

    ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        imgBackPress = findViewById(R.id.backPress);
        imgBackPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgotPassword.this, Login.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
                finish();
            }
        });

        signUpHint = findViewById(R.id.txtSignUpHint);
        String htmlString="<u>New User? Sign Up</u>";
        signUpHint.setText(Html.fromHtml(htmlString));

        inputEmail = findViewById(R.id.inputEmail);
        resetPwdBtn = findViewById(R.id.resetPwdBtn);
        progressBar = findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();

        resetPwdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });
    }

    public void callSignUp(View view) {
        TextView txtSignUpHint = findViewById(R.id.txtSignUpHint);

        Intent intent = new Intent(ForgotPassword.this, SignUp.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
    }

    private Boolean validateEmail() {
        String val = inputEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            inputEmail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            inputEmail.setError("Invalid email address");
            return false;
        } else {
            inputEmail.setError(null);
            inputEmail.setErrorEnabled(false);
            return true;
        }
    }

    public void resetPassword() {

        if (!validateEmail()) {
            return;
        }
        String email = inputEmail.getEditText().getText().toString();

        progressBar.setVisibility(View.VISIBLE);
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(ForgotPassword.this, "Check your email to reset your password!", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(ForgotPassword.this, Login.class);
                            startActivity(intent);
                            finish();
                        }
                    }, 3000);
                } else {
                    Toast.makeText(ForgotPassword.this, "Something went wrong! Try again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}