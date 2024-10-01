package com.example.hackathonapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InitialRegistrationActivity extends AppCompatActivity {
    private EditText teamNameEditText;
    private EditText teamSizeEditText;
    private Button nextButton;
    private Button previousButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_initial_registration);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        teamNameEditText = findViewById(R.id.teamNameEditText);
        teamSizeEditText = findViewById(R.id.teamSizeEditText);
        nextButton = findViewById(R.id.nextButton);
        previousButton = findViewById(R.id.previousButton);

        nextButton.setOnClickListener(v->{
            if(validateInput()) {
                String teamName = teamNameEditText.getText().toString();
                int teamSize = Integer.parseInt(teamSizeEditText.getText().toString());

                Intent intent = new Intent(this, TeamDetailsActivity.class);
                intent.putExtra("teamName", teamName);
                intent.putExtra("teamSize", teamSize);
                startActivity(intent);
            }
        });

        previousButton.setOnClickListener(v->finish());
    }


    private boolean validateInput(){
        boolean isValid = true;

        String teamName = teamNameEditText.getText().toString();
        String teamSizeStr = teamSizeEditText.getText().toString();

        if (TextUtils.isEmpty(teamName)){
            teamNameEditText.setError("Team name is required");
            isValid = false;
        }
        if (TextUtils.isEmpty(teamSizeStr)){
            teamSizeEditText.setError("Team size is required");
            isValid = false;
        }

        try {
            int teamSize = Integer.parseInt(teamSizeStr);
            if(teamSize < 3 || teamSize > 5){
                teamSizeEditText.setError("Team Size must be between 3 and 5");
                isValid = false;
            }
        } catch(NumberFormatException e){
            teamSizeEditText.setError("Invalid team size.");
            isValid = false;
        }

        return isValid;
    }

}

