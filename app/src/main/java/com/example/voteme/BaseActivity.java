package com.example.voteme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.voteme.databinding.ActivityBaseBinding;
import com.google.firebase.auth.FirebaseAuth;

public class BaseActivity extends AppCompatActivity {

    ActivityBaseBinding binding;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        String us = auth.getUid();
        binding.textView3.setText(us);

    }
}