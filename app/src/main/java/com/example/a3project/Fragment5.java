package com.example.a3project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.PrecomputedText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import org.w3c.dom.Text;


public class Fragment5 extends Fragment {
    TextView tv_name,tv_address;
    EditText edt_nick,edt_address;
    StringRequest stringRequest_login;
    Button btn_update,btn_order;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_5, container, false);

        tv_name = view.findViewById(R.id.tv_name);
        tv_address = view.findViewById(R.id.tv_address);
        btn_update = view.findViewById(R.id.btn_update);
        btn_order = view.findViewById(R.id.btn_order);


        Intent it_login = getActivity().getIntent();

        if(it_login.getStringExtra("id")!=null){
            SharedPreferences spf = getActivity().getApplicationContext().getSharedPreferences("basic", Context.MODE_PRIVATE);
            tv_name.setText(spf.getString("nick", ""));
            tv_address.setText(spf.getString("address", ""));
        }


        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it_update = new Intent(view.getContext(),Update.class);
                it_update.putExtra("id", it_login.getStringExtra("id"));
                startActivity(it_update);
            }
        });
        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it_order = new Intent(view.getContext(), myorder.class);
                startActivity(it_order);
            }
        });





        return view;
    }
}