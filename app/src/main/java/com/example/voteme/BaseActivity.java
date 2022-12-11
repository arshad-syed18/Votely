package com.example.voteme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.example.voteme.databinding.ActivityBaseBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class BaseActivity extends AppCompatActivity {

    ActivityBaseBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;
    ArrayList<voteQuestions> voteQuestionsArrayList;


    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        voteQuestionsArrayList = new ArrayList<>();

        String us = auth.getUid();
        binding.textView3.setText(us);

        String userName = Objects.requireNonNull(auth.getCurrentUser()).getDisplayName();
        binding.userWelcome.setText(String.format("Welcome, %s", userName));

        String randomKey = database.getReference().push().getKey();
        viewPagerAdapter adapter = new viewPagerAdapter(voteQuestionsArrayList);
        binding.viewPager2.setAdapter(adapter);

        binding.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                adapter.notifyDataSetChanged();
            }
        });

        //TODO add values from other website, for now 3 dummy values available to work with

        database.getReference()
                .child("voteQuestions")
                .child("active")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        voteQuestionsArrayList.clear();
                        i=0;
                        for(DataSnapshot snapshot1 : snapshot.getChildren()) {
                            voteQuestions voteQuestions = snapshot1.getValue(voteQuestions.class);
                            assert voteQuestions != null;
                            voteQuestions.setVoteQuesstionId(snapshot1.getKey());
                            voteQuestionsArrayList.add(voteQuestions);
                            adapter.notifyItemChanged(i++);
                        }
                        //adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });




    }
}