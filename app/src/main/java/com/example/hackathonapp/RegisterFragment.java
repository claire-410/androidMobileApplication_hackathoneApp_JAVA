package com.example.hackathonapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class RegisterFragment extends Fragment {
    private Button startRegistrationButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        startRegistrationButton = view.findViewById(R.id.startRegistrationButton);

        startRegistrationButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), InitialRegistrationActivity.class);
            startActivity(intent);
        });

        return view;
    }
}
