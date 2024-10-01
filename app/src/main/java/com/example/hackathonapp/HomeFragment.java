package com.example.hackathonapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class HomeFragment extends Fragment {
    private Button getStartedButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        getStartedButton = view.findViewById(R.id.getStartedButton);
        getStartedButton.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), InitialRegistrationActivity.class);
            startActivity(intent);
        });

        return view;
    }
}
