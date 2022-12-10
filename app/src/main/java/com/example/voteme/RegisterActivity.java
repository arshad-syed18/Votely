package com.example.voteme;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.voteme.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseStorage storage;
    private String name, email, password, imageUrl;
    Uri selectedImage;

    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri uri) {
                    if(uri !=null) {
                        binding.userImage.setImageURI(uri);
                        selectedImage =uri;
                        Toast.makeText(RegisterActivity.this, "Successful!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(RegisterActivity.this,"Please select an image!", Toast.LENGTH_SHORT).show();
                    }
                }
            });



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        storage=FirebaseStorage.getInstance();

        String packageName = getApplicationContext().getPackageName();
        selectedImage = Uri.parse("android.resource://"+packageName+"/drawable/avatar");
        System.out.println(selectedImage);
        System.out.println("android.resource://"+packageName+"/drawable/avatar");

        binding.userImage.setOnClickListener(view -> mGetContent.launch("image/*"));


        binding.signUpBtn.setOnClickListener(view -> {  //signup and auth with user
            name=binding.nameBox.getText().toString();
            email=binding.emailBox.getText().toString();
            password= Objects.requireNonNull(binding.passwordBox.getEditText()).getText().toString();

            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            Users user = new Users(name, email, selectedImage.toString());
                            StorageReference reference = storage.getReference().child("Profiles").child(Objects.requireNonNull(auth.getUid()));
                            reference.putFile(selectedImage).addOnCompleteListener(task1 -> {
                                if(task1.isSuccessful()){
                                    reference.getDownloadUrl().addOnSuccessListener(uri -> {
                                        imageUrl = uri.toString();
                                        user.setImageUri(imageUrl);
                                        database.getReference()
                                                .child("Users")
                                                .child(Objects.requireNonNull(auth.getUid()))
                                                .setValue(user);
                                    });
                                }
                            });
                            FirebaseUser userr = FirebaseAuth.getInstance().getCurrentUser();
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(name).build();

                            assert userr != null;
                            userr.updateProfile(profileUpdates).addOnCompleteListener(task12 -> {
                                Log.d(TAG, "createUserData: success");
                                Intent intent = new Intent(RegisterActivity.this, BaseActivity.class);
                                startActivity(intent);
                                finish();
                            });


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Registration failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });


        });

        binding.loginNow.setOnClickListener(view -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

    }
}