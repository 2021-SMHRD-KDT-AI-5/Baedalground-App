package com.example.a3project;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;


public class Fragment4 extends Fragment {

    RecyclerView rv_ordered;
    GestureDetector gestureDetector;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_4, container, false);

        rv_ordered = view.findViewById(R.id.rv_ordered);

        Bundle bundle = getArguments();

        rv_ordered.setLayoutManager(new LinearLayoutManager(getContext()));
//        ordered_Adapter adapter = new ordered_Adapter(ordered);
//        rv_ordered.setAdapter(adapter);

        return view;
    }
}