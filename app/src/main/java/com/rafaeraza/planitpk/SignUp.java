package com.rafaeraza.planitpk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    /*
    Declarations
     */
    ImageView imgBackPress;
    TextInputLayout inputName, inputEmail, inputPassword, inputConfirmPassword;
    TextView signUpHint, guestText;
    Button regBtn;

    private ProgressBar progressBar;

    FirebaseDatabase rootNode;
    DatabaseReference myRef;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUpHint = findViewById(R.id.txtSignUpHint);
        String htmlSUString = "<u>Already have an account? Sign In</u>";
        signUpHint.setText(Html.fromHtml(htmlSUString));

        guestText = findViewById(R.id.txtGuest);
        String htmlString="<u>Continue as GUEST</u>";
        guestText.setText(Html.fromHtml(htmlString));

        imgBackPress = findViewById(R.id.backPress);
        imgBackPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUp.this, Login.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
                finish();
            }
        });

        //Hooks
        inputName = findViewById(R.id.inputName);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        inputConfirmPassword = findViewById(R.id.inputConfirmPassword);
        regBtn = findViewById(R.id.regBtn);

        mAuth = FirebaseAuth.getInstance();
    }

    //=============================================================================================//
    /*
    Function to start Log In activity
     */
    public void callLogin(View view) {
        TextView txtSignUpHint = findViewById(R.id.txtSignUpHint);

        Intent intent = new Intent(SignUp.this, Login.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
    }

    /*
    Function to validate name
     */
    private Boolean validateName() {
        String val = inputName.getEditText().getText().toString();

        if (val.isEmpty()) {
            inputName.setError("Field cannot be empty");
            return false;
        } else {
            inputName.setError(null);
            inputName.setErrorEnabled(false);
            return true;
        }
    }
    /*
    Function to validate email
     */
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
    /*
    Function to validate password
     */
    private Boolean validatePassword() {
        String val = inputPassword.getEditText().getText().toString();
        String conPass = inputConfirmPassword.getEditText().getText().toString();
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
            inputPassword.setError("Password must contain\n1 digit, 1 lower case letter,\n1 upper case letter, 1 special character");
            return false;
        } else if (!val.equals(conPass)){
            inputConfirmPassword.setError("Passwords do not match");
            return false;
        } else {
            inputPassword.setError(null);
            inputPassword.setErrorEnabled(false);
            return true;
        }
    }
    /*
    Function to sign up user using Firebase Auth
     */
    public void registerUser(View view) {

        rootNode = FirebaseDatabase.getInstance();
        myRef = rootNode.getReference("Users");

        if (!validateName() | !validatePassword() | !validateEmail()) {
            return;
        }

        // Get all values
        String name = inputName.getEditText().getText().toString();
        String email = inputEmail.getEditText().getText().toString();
        String password = inputPassword.getEditText().getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(name, email, password, "", "");

                            myRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(SignUp.this, "User has been registered successfully", Toast.LENGTH_LONG).show();
                                        ProgressDialog progressDialog = new ProgressDialog(SignUp.this);
                                        progressDialog.setTitle("Signing you in...");
                                        progressDialog.show();
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                Intent intent = new Intent(SignUp.this, SelectInterests.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        }, 3000);
                                    } else {
                                        Toast.makeText(SignUp.this, "Failed to register user! Try again", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(SignUp.this, "Failed to register user! Try again", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void skip(View view) {
        startActivity(new Intent(this, Explore.class));
        finish();
    }
}