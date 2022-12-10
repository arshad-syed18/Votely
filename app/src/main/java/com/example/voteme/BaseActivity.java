package com.example.voteme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.voteme.databinding.ActivityBaseBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class BaseActivity extends AppCompatActivity {

    ActivityBaseBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;
    ArrayList<voteQuestions> voteQuestionsArrayList;

    String questionText;
    ArrayList<String> options;
    ArrayList<Integer> voteCounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        String us = auth.getUid();
        binding.textView3.setText(us);

        String userName = Objects.requireNonNull(auth.getCurrentUser()).getDisplayName();
        binding.userWelcome.setText(String.format("Welcome, %s", userName));


        //TODO dummy values here need to get from database new child etc
        voteQuestionsArrayList = new ArrayList<>();
        options = new ArrayList<>();
        voteCounts = new ArrayList<>();

        questionText = "Hey! This is a question that is gonna be multiple lines" +
                "How doesit look? Please give feedback!";
        options.add("Option111");
        options.add("Option2222");
        voteCounts.add(5);
        voteCounts.add(5);
        voteQuestions voteQuestions = new voteQuestions(questionText, options, voteCounts);
        voteQuestionsArrayList.add(voteQuestions);

        viewPagerAdapter adapter = new viewPagerAdapter(voteQuestionsArrayList);
        binding.viewPager2.setAdapter(adapter);

    }
}