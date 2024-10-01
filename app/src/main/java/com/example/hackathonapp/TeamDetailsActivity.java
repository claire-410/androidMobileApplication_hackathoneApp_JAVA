package com.example.hackathonapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class TeamDetailsActivity extends AppCompatActivity {
    private LinearLayout memberDetailsLayout;
    private Button submitButton;
    private Button goBackButton;
    private String teamName;
    private int teamSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_team_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        memberDetailsLayout = findViewById(R.id.memberDetailsLayout);
        submitButton = findViewById(R.id.submitButton);
        goBackButton = findViewById(R.id.goBackButton);
        teamName = getIntent().getStringExtra("teamName");
        teamSize = getIntent().getIntExtra("teamSize", 0);

        for(int i=0; i<teamSize; i++){
            addMemberFiedls(i+1);
        }

        submitButton.setOnClickListener(v -> {
            if(validateInput()) {
                ArrayList<String> memberNames = new ArrayList<>();
                ArrayList<String> memberEmails = new ArrayList<>();

                for (int i = 0; i < teamSize; i++) {
                    EditText nameEditText = (EditText) memberDetailsLayout.getChildAt(i * 2);
                    EditText emailEditText = (EditText) memberDetailsLayout.getChildAt(i * 2 + 1);
                    memberNames.add(nameEditText.getText().toString());
                    memberEmails.add(emailEditText.getText().toString());
                }

                Intent intent = new Intent(this, RegistrationSummaryActivity.class);
                intent.putExtra("teamName", teamName);
                intent.putExtra("teamSize", teamSize);
                intent.putStringArrayListExtra("memberNames", memberNames);
                intent.putStringArrayListExtra("memberEmails", memberEmails);
                startActivity(intent);
            }
        });

        goBackButton.setOnClickListener(v -> finish());
    }

    private void addMemberFiedls(int memberNumber){
        EditText nameEditText = new EditText(this);
        nameEditText.setHint("Member " + memberNumber + " Name");
        memberDetailsLayout.addView(nameEditText);

        EditText emailEditText = new EditText(this);
        emailEditText.setHint("Member " + memberNumber + " Email");
        memberDetailsLayout.addView(emailEditText);
    }

    private boolean validateInput(){
        boolean isValid = true;

        for(int i=0; i<teamSize; i++) {
            EditText nameEditText = (EditText) memberDetailsLayout.getChildAt(i * 2);
            EditText emailEditText = (EditText) memberDetailsLayout.getChildAt(i * 2 + 1);

            String name = nameEditText.getText().toString();
            String email = emailEditText.getText().toString();

            if(TextUtils.isEmpty(name)){
                nameEditText.setError("Name is required");
                isValid = false;
            } else if (name.length() < 4){
                nameEditText.setError("Name Must be at least 4 characters long");
                isValid = false;
            }

            if(TextUtils.isEmpty(email)){
                emailEditText.setError("Email is required");
                isValid = false;
            } else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                emailEditText.setError("Invalid email format");
                isValid = false;
            }
        }

        if(!isValid){
            Toast.makeText(this, "Please correct the errors", Toast.LENGTH_SHORT).show();
        }

        return isValid;
    }

}