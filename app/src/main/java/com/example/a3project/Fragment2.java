package com.example.a3project;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class Fragment2 extends Fragment {
    CheckBox cb_1, cb_2, cb_3, cb_4;
    TextView tv_test;
    String resultText = "";
    Button btn_top10;
    RadioButton rb_1, rb_2, rb_3;
    ListView listview;
    String age = null;

    RequestQueue requestQueue;
    StringRequest stringRequest_top10;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //fragment2 주석추가
        View view = inflater.inflate(R.layout.fragment_2, container, false);

        cb_1 = view.findViewById(R.id.cb_1);
        cb_2 = view.findViewById(R.id.cb_2);
        cb_3 = view.findViewById(R.id.cb_3);
        cb_4 = view.findViewById(R.id.cb_4);
        rb_1 = view.findViewById(R.id.rb_1);
        rb_2 = view.findViewById(R.id.rb_2);
        rb_3 = view.findViewById(R.id.rb_3);
        ListView listview = view.findViewById(R.id.listview);
        btn_top10 = view.findViewById(R.id.btn_top10);

        cb_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_1.isChecked()) {
                    cb_2.setChecked(false);
                    cb_3.setChecked(false);
                    cb_4.setChecked(false);
                    age = cb_1.getText().toString();
                }
            }
        });

        cb_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_2.isChecked()) {
                    cb_1.setChecked(false);
                    cb_3.setChecked(false);
                    cb_4.setChecked(false);
                    age = cb_2
                            .getText().toString();
                }
            }
        });

        cb_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_3.isChecked()) {
                    cb_1.setChecked(false);
                    cb_2.setChecked(false);
                    cb_4.setChecked(false);
                    age = cb_3.getText().toString();
                }
            }
        });

        cb_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_4.isChecked()) {
                    cb_1.setChecked(false);
                    cb_2.setChecked(false);
                    cb_3.setChecked(false);
                    age = cb_4.getText().toString();
                }
            }
        });


        return view;

    }
}