package com.rafaeraza.planitpk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.util.Objects;

public class EditProfile extends AppCompatActivity {

    ImageView imgBackPress, profileImg;
    MaterialButton selectProfileImgBtn;
    TextInputLayout userNameLayout;
    TextInputEditText userNameEditText;
    MaterialButton updateBtn;

    ProgressBar progressBar;
    View loadingBackground;

    String userID, userDPString, userName;
    Uri dpUri;

    FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference dbRefUser;
    DatabaseReference dbRefDP;
    DatabaseReference dbRefName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        imgBackPress = findViewById(R.id.backPress);
        imgBackPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        userID = mAuth.getCurrentUser().getUid();

        profileImg = findViewById(R.id.imgProfilePicture);
        selectProfileImgBtn = findViewById(R.id.btnSelectProfileImg);
        userNameLayout = findViewById(R.id.userNameLayout);
        userNameEditText = findViewById(R.id.userNameET);
        updateBtn = findViewById(R.id.btnUpdate);

        progressBar = findViewById(R.id.editProfileLoader);
        loadingBackground = findViewById(R.id.loading_background);

        profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.Companion.with(EditProfile.this)
                        .cropSquare()
                        .compress(1024)
                        .maxResultSize(1080, 1080)
                        .start(10);
            }
        });

        getUserData();

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                loadingBackground.setVisibility(View.VISIBLE);

                String sName = Objects.requireNonNull(userNameEditText.getText()).toString();
                updateUserData(userID, sName, dpUri);
            }
        });

        userNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                userNameLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void getUserData() {

        dbRefUser = db.getReference("Users/" + userID);

        dbRefUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    userName = Objects.requireNonNull(snapshot.child("name").getValue()).toString();
                    userDPString = snapshot.child("profilePicture").getValue().toString();

                    if (!userDPString.isEmpty()) {
                        Glide.with(EditProfile.this)
                                .load(userDPString)
                                .placeholder(R.drawable.vector_user_green)
                                .error(R.drawable.vector_user_green)
                                .centerCrop()
                                .into(profileImg);
                    } else {
                        Glide.with(EditProfile.this)
                                .load(R.drawable.vector_user_green)
                                .centerCrop()
                                .into(profileImg);
                    }

                    userNameEditText.setText(userName);

                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });


    }

    private void updateUserData(String id, String uName, Uri uDP) {

        dbRefName = db.getReference("Users/" + userID).child("name");

        if (uDP != null) {
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageReference = storage.getReference();
            storageReference = storageReference.child("UserImages/" +
                    Objects.requireNonNull(mAuth.getCurrentUser()).getEmail() +
                    ".jpg");

            if (!uName.equals(userName)) {
                if (uName.length() == 0) {
                    progressBar.setVisibility(View.GONE);
                    loadingBackground.setVisibility(View.GONE);
                    userNameLayout.setError("Name cannot be empty");
                    return;
                }
                dbRefName.setValue(uName);
            }

            storageReference.putFile(uDP)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> task = taskSnapshot.getStorage().getDownloadUrl();
                            task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    progressBar.setVisibility(View.GONE);
                                    loadingBackground.setVisibility(View.GONE);
                                    String image = uri.toString();
                                    dbRefDP = db.getReference("Users")
                                            .child(id)
                                            .child("profilePicture");
                                    dbRefDP.setValue(image);

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressBar.setVisibility(View.GONE);
                                    loadingBackground.setVisibility(View.GONE);
                                    Toast.makeText(EditProfile.this, "Image Not Uploaded", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressBar.setVisibility(View.GONE);
                            loadingBackground.setVisibility(View.GONE);
                            Toast.makeText(EditProfile.this, "Image Uploading Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
            dpUri = null;
        } else if (!uName.equals(userName)) {
            if (uName.length() == 0) {
                progressBar.setVisibility(View.GONE);
                loadingBackground.setVisibility(View.GONE);
                userNameLayout.setError("Name cannot be empty");
                return;
            }
            dbRefName.setValue(uName);
            progressBar.setVisibility(View.GONE);
            loadingBackground.setVisibility(View.GONE);
            Toast.makeText(EditProfile.this, "Name updated successfully", Toast.LENGTH_LONG).show();
        } else {
            progressBar.setVisibility(View.GONE);
            loadingBackground.setVisibility(View.GONE);
        }

    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 10) {
            Uri uri = data.getData();
            if (uri != null) {
                dpUri = uri;
                profileImg.setImageURI(uri);
                userDPString = uri.toString();
            }
        }

        if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, "Unable to open gallery", Toast.LENGTH_SHORT).show();
        }
    }
}