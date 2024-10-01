package com.example.hackathonapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class RegistrationSummaryActivity extends AppCompatActivity {
    private TextView summaryTextView;
    private Button goHomeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration_summary);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        summaryTextView = findViewById(R.id.summaryTextView);
        goHomeButton = findViewById(R.id.goHomeButton);

        String teamName = getIntent().getStringExtra("teamName");
        int teamSize = getIntent().getIntExtra("teamSize", 0);
        ArrayList<String> memberNames = getIntent().getStringArrayListExtra("memberNames");
        ArrayList<String> memberEmails = getIntent().getStringArrayListExtra("memberEmails");

        StringBuilder summary = new StringBuilder();
        summary.append("Team Name: ").append(teamName).append("\n");
        summary.append("Team Size: ").append(teamSize).append("\n\n");
        summary.append("Team Members: \n");

        for(int i=0; i<teamSize; i++){
            summary.append("Member ").append(i+1).append(":\n");
            summary.append("Name: ").append(memberNames.get(i)).append("\n");
            summary.append("Email: ").append(memberEmails.get(i)).append("\n\n");
        }

        summaryTextView.setText(summary.toString());

        goHomeButton.setOnClickListener(v->{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

    }



}