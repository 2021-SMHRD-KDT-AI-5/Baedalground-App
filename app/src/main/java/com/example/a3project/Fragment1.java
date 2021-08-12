package com.example.a3project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class Fragment1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        EditText edt_select;
        Button btn_select;
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        edt_select = view.findViewById(R.id.edt_select);
        btn_select = view.findViewById(R.id.btn_select);

        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_select.getText().toString();

            }
        });

        return view;

    }
}