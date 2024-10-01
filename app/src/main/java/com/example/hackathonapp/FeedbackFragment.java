package com.example.hackathonapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;


public class FeedbackFragment extends Fragment {
    private RadioGroup ratingRadioGroup;
    private EditText commentEditText;
    private Button submitButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);

        ratingRadioGroup = view.findViewById(R.id.ratingRadioGroup);
        commentEditText = view.findViewById(R.id.commentEditText);
        submitButton = view.findViewById(R.id.submitButton);

        submitButton.setOnClickListener(v->{
            int selectedRatingId = ratingRadioGroup.getCheckedRadioButtonId();
            String comment = commentEditText.getText().toString();

            if (selectedRatingId != -1 && !comment.isEmpty()) {
                Toast.makeText(getContext(), "Feedback submitted successfully!", Toast.LENGTH_SHORT).show();
                ratingRadioGroup.clearCheck();
                commentEditText.setText("");
            } else {
                Toast.makeText(getContext(), "Please provide both a rating and a comment", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}

