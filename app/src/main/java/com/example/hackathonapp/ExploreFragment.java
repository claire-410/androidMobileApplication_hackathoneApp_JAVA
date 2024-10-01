package com.example.hackathonapp;

import android.os.Bundle;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;


public class ExploreFragment extends Fragment {
    private SwitchCompat switchButton;
    private ListView categoriesListView;
    private ArrayList<String> allCategories;
    private ArrayAdapter<String> adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);

        switchButton = view.findViewById(R.id.switchButton);
        categoriesListView = view.findViewById(R.id.categoriesListView);

        allCategories = new ArrayList<>(Arrays.asList("Web", "Mobile", "AI/ML", "IoT", "Blockchain"));
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, new ArrayList<>(allCategories.subList(0, 3)));
        categoriesListView.setAdapter(adapter);

        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    adapter.clear();
                    adapter.addAll(allCategories);
                }else{
                    adapter.clear();
                    adapter.addAll(allCategories.subList(0, 3));
                }
                adapter.notifyDataSetChanged();
            }
        });

        return view;
    }
}

