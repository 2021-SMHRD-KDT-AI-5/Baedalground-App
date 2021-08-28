package com.example.a3project;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;


public class Fragment2 extends Fragment {
    CheckBox cb_1, cb_2, cb_3, cb_4;
    EditText edt_myaddr;
    Button btn_top10;
    RecyclerView recyclerView;
    String age = null;
    String gender = null;

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

        String[] cb_gp = new String[4];
        cb_gp[0] = "cb_1";
        cb_gp[1] = "cb_2";
        cb_gp[2] = "cb_3";
        cb_gp[3] = "cb_4";

        recyclerView = view.findViewById(R.id.recyclerView);
        btn_top10 = view.findViewById(R.id.btn_top10);

        for (int i = 0; i < 4; i++) {
            if (cb_1.isChecked()) {

            }


            requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

            stringRequest_top10 = new StringRequest(Request.Method.POST, "http://172.30.1.54:8090/p3_server/Top10Servlet", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("1")) {
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "실패...", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getActivity().getApplicationContext(), "test", Toast.LENGTH_SHORT).show();
                    error.printStackTrace();
                }
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();

                    String myaddr = edt_myaddr.getText().toString();

                    params.put("age", age);
                    params.put("gender", gender);
                    params.put("myaddr", myaddr);

                    return params;
                }
            };

            btn_top10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    requestQueue.add(stringRequest_top10);
                }
            });

        }
        return view;
    }
}
