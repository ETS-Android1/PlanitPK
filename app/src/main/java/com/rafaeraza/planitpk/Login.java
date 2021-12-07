package com.rafaeraza.planitpk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    //Variables
    TextInputLayout inputEmail, inputPassword;
    TextView guestText;
    Button loginBtn;

    TextView forgotPwd;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        guestText = findViewById(R.id.txtGuest);
        String htmlString="<u>Continue as GUEST</u>";
        guestText.setText(Html.fromHtml(htmlString));
        guestText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginAsGuest(view);
            }
        });

        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);

        loginBtn = findViewById(R.id.loginBtn);

        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null) {
            Intent intent = new Intent(Login.this, Explore.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
        }
    }

    public void callSignUp(View view) {
        TextView txtSignUpHint = findViewById(R.id.txtSignUpHint);

        Intent intent = new Intent(Login.this, SignUp.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
    }


    public void callForgotPassword(View view) {
        TextView txtForgotPassword = findViewById(R.id.txtForgotPassword);

        Intent intent = new Intent(Login.this, ForgotPassword.class);
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

    private Boolean validatePassword() {
        String val = inputPassword.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            inputPassword.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            inputPassword.setError("Password is too weak");
            return false;
        } else {
            inputPassword.setError(null);
            inputPassword.setErrorEnabled(false);
            return true;
        }
    }

    public void loginUser(View view) {

        if (!validatePassword() | !validateEmail()) {
            return;
        }

        // Get all values
        String email = inputEmail.getEditText().getText().toString();
        String password = inputPassword.getEditText().getText().toString();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    ProgressDialog progressDialog = new ProgressDialog(Login.this);
                    progressDialog.setTitle("Signing you in...");
                    progressDialog.show();
                    Intent intent = new Intent(Login.this, Explore.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
                } else {
                    Toast.makeText(Login.this, "Failed to login! Please check your credentials and try again", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void loginAsGuest(View view) {

        mAuth.signInWithEmailAndPassword("guest@guest.com", "guest@123").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    ProgressDialog progressDialog = new ProgressDialog(Login.this);
                    progressDialog.setTitle("Signing you in...");
                    progressDialog.show();
                    Intent intent = new Intent(Login.this, Explore.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
                } else {
                    Toast.makeText(Login.this, "Failed to login! Please check your credentials and try again", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void skip(View view) {
        startActivity(new Intent(this, Explore.class));
        finish();
    }
}